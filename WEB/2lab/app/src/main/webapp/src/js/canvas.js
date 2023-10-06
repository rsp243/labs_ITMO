window.onload = function () {
    drawBeginnigGraph()
    canvasClick()
}

export function drawBeginnigGraph() {
    let canvas = document.getElementById("canvas"),
        ctx = canvas.getContext('2d');

    ctx.beginPath();
    // setting blue color
    ctx.strokeStyle = "#3399ff";
    ctx.fillStyle = "#3399ff";

    // filling area
    // rectangle
    ctx.fillRect(canvas.width / 2, canvas.height / 2, canvas.width / 3, canvas.height / 3);
    ctx.moveTo(canvas.width / 2, canvas.height / 2);
    // 1/4 of circle
    ctx.arc(canvas.width / 2, canvas.height / 2, canvas.width / 3, -3.14, 1.57, 1);
    ctx.fill();
    // triangle
    ctx.moveTo(canvas.width / 2, canvas.height / 2);
    ctx.lineTo(canvas.width / 2, canvas.height / 6);
    ctx.lineTo(canvas.width / 1.2, canvas.height / 2);
    ctx.lineTo(canvas.width / 2, canvas.height / 2);
    ctx.fill();

    // setting black color
    ctx.fillStyle = "black";
    ctx.font = "12px serif";

    // vertical 'R' marks
    ctx.fillText("R", canvas.width / 1.95, canvas.height / 5.5);
    ctx.fillText("R/2", canvas.width / 1.93, canvas.height / 2.85);
    ctx.fillText("-R/2", canvas.width / 1.93, canvas.height / 1.475);
    ctx.fillText("-R", canvas.width / 1.93, canvas.height / 1.184);

    // horizontal 'R' marks
    ctx.fillText("-R", canvas.width / 6.7, canvas.height / 2.05);
    ctx.fillText("-R/2", canvas.width / 3.38, canvas.height / 2.05);
    ctx.fillText("R/2", canvas.width / 1.59, canvas.height / 2.05);
    ctx.fillText("R", canvas.width / 1.23, canvas.height / 2.05);
    ctx.stroke();

    ctx.beginPath();
    ctx.strokeStyle = "black";
    // graph arrows
    // vertical arrow
    ctx.moveTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2, canvas.height);
    ctx.moveTo(canvas.width / 1.95, canvas.height * 0.03);
    ctx.lineTo(canvas.width / 2, 0);
    ctx.moveTo(canvas.width / 2.05, canvas.height * 0.03);
    ctx.lineTo(canvas.width / 2, 0);
    // horizontal arrow
    ctx.moveTo(0, canvas.height / 2);
    ctx.lineTo(canvas.width, canvas.height / 2);
    ctx.moveTo(canvas.width * 0.97, canvas.height / 1.95);
    ctx.lineTo(canvas.width, canvas.height / 2);
    ctx.moveTo(canvas.width * 0.97, canvas.height / 2.05);
    ctx.lineTo(canvas.width, canvas.height / 2);

    // R unit marks
    // vertical marks
    ctx.moveTo(canvas.width / 2.04, canvas.height / 1.20);
    ctx.lineTo(canvas.width / 1.96, canvas.height / 1.20);
    ctx.moveTo(canvas.width / 2.04, canvas.height / 1.5);
    ctx.lineTo(canvas.width / 1.96, canvas.height / 1.5);
    ctx.moveTo(canvas.width / 2.04, canvas.height / 3);
    ctx.lineTo(canvas.width / 1.96, canvas.height / 3);
    ctx.moveTo(canvas.width / 2.04, canvas.height / 6);
    ctx.lineTo(canvas.width / 1.96, canvas.height / 6);

    // horizontal marks
    ctx.moveTo(canvas.width / 1.2, canvas.height / 2.04);
    ctx.lineTo(canvas.width / 1.2, canvas.height / 1.96);
    ctx.moveTo(canvas.width / 1.5, canvas.height / 2.04);
    ctx.lineTo(canvas.width / 1.5, canvas.height / 1.96);
    ctx.moveTo(canvas.width / 3, canvas.height / 2.04);
    ctx.lineTo(canvas.width / 3, canvas.height / 1.96);
    ctx.moveTo(canvas.width / 6, canvas.height / 2.04);
    ctx.lineTo(canvas.width / 6, canvas.height / 1.96);
    ctx.stroke();
}

export function drawPoint(xValue, yValue, rValue, color) {
    let canvas = document.getElementById("canvas"),
        ctx = canvas.getContext('2d');

    ctx.beginPath();

    ctx.strokeStyle = color;
    ctx.fillStyle = color;
    let xOnCanvas = canvas.width / 2 + canvas.width / 3 * (xValue / rValue)
    let yOnCanvas = canvas.width / 2 - canvas.width / 3 * (yValue / rValue)
    ctx.arc(xOnCanvas, yOnCanvas, 4, 0, 2 * Math.PI);
    ctx.fill();

    ctx.stroke();
}

function canvasClick() {
    let canvas = document.getElementById("canvas");
    canvas.addEventListener('click', (event) => {
        let rValue =  $('input[type="checkbox"]:checked:first').val()
        if (!rValue) {
            alert("R value is not set")
            return
        }
        $('input:checkbox').not(this).prop('checked', false);

        // need to fix : * rNum both xValue and yValue
        let rFloat = parseFloat(rValue)
        let xValue = (event.offsetX - canvas.width / 2) / (canvas.width / 3) * rFloat
        let yValue = - (event.offsetY - canvas.height / 2) / (canvas.height / 3) * rFloat

        let xNum = Math.round(parseFloat(xValue))
        let yFloat = parseFloat(yValue)

        let queryString = "xVal=" + xNum + "&yVal=" + yFloat + "&rVal=" + rFloat  
        fetch(new URL("controller?" + queryString, window.location.href), {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        }).then(responseCatched => {
            if (responseCatched.ok) {
                location.reload()
                return
            }
            throw new Error(responseCatched.statusText)
        })
    })
}


export function clearCanvas() {
    let canvas = document.getElementById("canvas"),
        ctx = canvas.getContext('2d');

    ctx.clearRect(0, 0, canvas.width, canvas.width)

}