function addOneRowToTable(array) {
    let row_html_code = "<tr>\n"
    for (element in array) {
        row_html_code += array[element] + "\n"
    }
    row_html_code += "</tr>\n"
    console.log(row_html_code)
    $('.data-table table .table-body').prepend(row_html_code)
    $('.data-table table .table-body tr:first-child').animate({
        backgroundColor: "#1569bd8c",
    }, 400).animate({
        backgroundColor: "rgba(185, 180, 206, 0.786)",
    }, 500);
    console.log("successfully animated");
}