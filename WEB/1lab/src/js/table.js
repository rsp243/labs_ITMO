import { clearCanvas } from "./canvas.js"

export function addOneRowToTable(array) {
    let row_html_code = "<tr>\n"
    for (let element in array) {
        row_html_code += array[element] + "\n"
    }
    row_html_code += "</tr>\n"
    let localStorageTable = localStorage.getItem("table")
    console.log(localStorageTable)
    localStorage.removeItem("table")
    localStorage.setItem("table", row_html_code + "\n" + localStorageTable)
    $('.data-table table .table-body').prepend(row_html_code)
    $('.data-table table .table-body tr:first-child').animate({
        backgroundColor: "#1569bd8c",
    }, 400).animate({
        backgroundColor: "rgba(185, 180, 206, 0)",
    }, 500);
    console.log(localStorage.getItem("table"))
}

export function addTableFromLocalStorage() {
    let localStorageTable = localStorage.getItem("table")
    $('.data-table table .table-body').prepend(localStorageTable)
}

export function clearTable() {
    $('.data-table table .table-body').text("")
    localStorage.removeItem("table")
    clearCanvas()
}