CREATE OR REPLACE PROCEDURE convert_int_columns_to_date(IN tab_name TEXT)
LANGUAGE plpgsql
AS $$
DECLARE
    col_name TEXT;
    date_col_name TEXT;
    count_int_columns INT;
    count_added INT;
    is_table_exists BOOLEAN;
    is_primary_key BOOLEAN;
BEGIN
    count_int_columns := 0;
    count_added := 0;

    SELECT EXISTS (
        SELECT 1 FROM information_schema.tables 
        WHERE table_name = tab_name
    ) INTO is_table_exists;
    IF NOT is_table_exists THEN
        RAISE NOTICE '''
ERROR: Table name "%" not found in DB
''', tab_name; 
        RETURN;
    END IF;

    FOR col_name IN
        SELECT column_name
        FROM information_schema.columns 
        WHERE table_name = tab_name
          AND data_type = 'integer'
    LOOP
        SELECT EXISTS (
            SELECT 1
            FROM information_schema.key_column_usage
            WHERE table_name = tab_name
              AND column_name = col_name
              AND constraint_name LIKE '%pkey%'
        ) INTO is_primary_key;

        count_int_columns := count_int_columns + 1;
        IF is_primary_key THEN
            CONTINUE;
        END IF;

        date_col_name := col_name || '_DATE';

        BEGIN
        EXECUTE format('ALTER TABLE %I ADD COLUMN %I DATE', tab_name, date_col_name);
        EXECUTE format('
            UPDATE %I
            SET %I = TO_DATE(
                CAST(
                    CASE
                        WHEN %I > 5874897 THEN %I %% 5874897
                        WHEN %I < -4713 THEN %I %% -4713
                        ELSE %I
                    END
                AS VARCHAR),
                ''YYYY-MM-DD''
            )
        ', tab_name, date_col_name, col_name, col_name, col_name, col_name, col_name);
        count_added := count_added + 1;

        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'ERROR: Failed to compute column "%I": %', col_name, SQLERRM;
        END;
    END LOOP;
    RAISE NOTICE '''
RESULT:
Таблица: %
Целочисленных столбцов: %
Столбцов добавлено: %
''', tab_name, count_int_columns, count_added;
END;
$$;
