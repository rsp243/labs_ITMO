import { drawPoint, drawBeginnigGraph } from './canvas.js'
import { isFloat, validateEntryData } from './validation.js'
import { addOneRowToTable, clearTable } from './table.js'

$(".btn-process").on("click", async function () {
    let xValue = $("input[name=\'Xvalue\']:checked").val();
    let yValue = $(".Yselection-text").val();
    let rValue = $("select[name=\'Rvalue\'] option:selected").val();
    let xFloat = parseFloat(isFloat(xValue));
    let yFloat = parseFloat(isFloat(yValue));
    let rFloat = parseFloat(isFloat(rValue));

    if (validateEntryData(xFloat, yFloat, rFloat)) {
        let requestBody = { "X": xFloat, "Y": yFloat, "R": rFloat }
        let response = await fetch(new URL("src/php/index.php", window.location.href), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        })
            .then(responseCatched => {
                if (responseCatched.ok) {
                    return responseCatched.json()
                }
                throw new Error(responseCatched.statusText)
            })
        addOneRowToTable(response);
        for (let element in response) {
            response[element] = response[element].replace("<td scope=\"row\">", "").replace("<\/td>", "")
        }
        drawPoint(response["xValue"], response["yValue"], response['rValue'], response['color(RGB)'])
    }
})

$(".btn-clear").on("click", function () {
    if (confirm('Are you sure you want to clear data table?')) {
        clearTable()
        drawBeginnigGraph()
    }
})