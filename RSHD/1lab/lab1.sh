TABLE_NAME=$1

if [[ -z "$TABLE_NAME" ]]; then
    echo "No TABLE_NAME was passed to script. Default: test"
    TABLE_NAME="test"
fi

DB_NAME='studs'
HOST_NAME='pg'

psql -h pg -d studs -t -A -f script.sql 2>&1 > /dev/null

output=$(psql -h "$HOST_NAME" -d "$DB_NAME" -c "CALL convert_int_columns_to_date('$TABLE_NAME');" 2>&1 | tail -n+2)

result=$(echo "$output" | grep "RESULT")

if [[ -z "$result" ]]; then
    echo "ERROR: Couldnt to get result from sql script"
    echo "LOG:"
    echo "$output" | sed '$d' | sed '$d'
    exit 1
fi

output=$(echo "$output" | tail -n+3 | head -n+3)

echo "$output"
