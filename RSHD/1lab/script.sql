-- select column_name from information_schema.columns where table_name = 'Н_ЛЮДИ' and data_type = 'integer' and column_name = 'ИД';

CREATE OR REPLACE PROCEDURE convert_int_columns_to_date(tab_name TEXT)
LANGUAGE plpgsql
AS $$
DECLARE
    col_name TEXT;
    date_col_name TEXT;
    count_int_columns INT;
    count_added INT;
    is_primary_key BOOLEAN;
BEGIN
    count_int_columns := 0;
    count_added := 0;
    FOR col_name IN
        SELECT column_name
        FROM information_schema.columns 
        WHERE table_name = convert_int_columns_to_date.tab_name
          AND data_type = 'integer'
    LOOP
        SELECT EXISTS (
            SELECT 1
            FROM information_schema.key_column_usage
            WHERE table_name = convert_int_columns_to_date.tab_name
              AND column_name = col_name
              AND constraint_name LIKE '%pkey%'
        ) INTO is_primary_key;

        count_int_columns := count_int_columns + 1;
        IF is_primary_key THEN
            CONTINUE;
        END IF;

        date_col_name := col_name || '_DATE';

        EXECUTE format('ALTER TABLE %I ADD COLUMN %I DATE', convert_int_columns_to_date.tab_name, date_col_name);
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
        ', convert_int_columns_to_date.tab_name, date_col_name, col_name, col_name, col_name, col_name, col_name);

        count_added := count_added + 1;
    END LOOP;
    RAISE NOTICE '''
RESULT:
Таблица: %
Целочисленных столбцов: %
Столбцов добавлено: %
''', convert_int_columns_to_date.tab_name, count_int_columns, count_added;
END;
$$;
