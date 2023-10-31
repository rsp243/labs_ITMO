import { clearCanvas } from "./canvas.js"

export function clearTable() {
    $('.data-table table .table-body').text("")
    localStorage.removeItem("table")
    clearCanvas()
}