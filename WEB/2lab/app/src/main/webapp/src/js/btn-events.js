import { drawPoint, drawBeginnigGraph } from './canvas.js'
import { isFloat, validateEntryData } from './validation.js'
import { addOneRowToTable, clearTable } from './table.js'

$(".btn-clear").on("click", function () {
    if (confirm('Are you sure you want to clear data table?')) {
        clearTable()
        drawBeginnigGraph()
        window.location.replace("v1/clear");
    }
})