function addOneRowToTable(array) {
    let row_html_code = "<tr>\n"
    for (element in array) {
        row_html_code += array[element] + "\n"
    }
    row_html_code += "<td>" + "1" + "</td>\n"
    row_html_code += "</tr>\n"
    console.log(row_html_code)
    $('.data-table table .table-body').prepend(row_html_code);
}