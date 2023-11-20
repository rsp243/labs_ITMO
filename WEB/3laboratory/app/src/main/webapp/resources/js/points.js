let canvas = document.getElementById("canvas"),
    ctx = canvas.getContext('2d');

function drawPointWithHeightWeightXYOffset(xValue, yValue, rValue, img, offsetX, offsetY, scale, rotationAngle) {
    let xOnCanvas = canvas.width / 2 + canvas.width / 3 * (xValue / rValue)
    let yOnCanvas = canvas.width / 2 - canvas.width / 3 * (yValue / rValue)

    // let xOnCanvasOff = canvas.width / 2 + canvas.width / 3 * (xValue / rValue) + offsetX
    // let yOnCanvasOff = canvas.width / 2 - canvas.width / 3 * (yValue / rValue) + offsetY

    // let rotation = degrees * Math.PI / 180
    ctx.setTransform(scale, 0, 0, scale, xOnCanvas, yOnCanvas); // sets scale and origin
    ctx.rotate(rotationAngle);
    ctx.drawImage(img, -img.width / 2 - offsetX, -img.height / 2 - offsetY);
}

function drawIsHitPoint(xValue, yValue, rValue, isHit) {

    let axeImg = new Image()
    let fissureImg = new Image()
    let axeHeight = 40
    let fissureHeight = 25
    let axeDegrees = 0


    fissureImg.onload = function () { 
        let fissureScale = fissureHeight / fissureImg.height

        let fissureDegrees = Math.random() * 360
        let rotation = fissureDegrees * Math.PI / 180
        drawPointWithHeightWeightXYOffset(xValue, yValue, rValue, fissureImg, 0, 0, fissureScale, rotation)
    };
    fissureImg.src = fissureImageSrc

    axeImg.onload = function () {

        let axeScale = axeHeight / axeImg.height

        let rotation = axeDegrees * Math.PI / 180
        drawPointWithHeightWeightXYOffset(xValue, yValue, rValue, axeImg, axeHeight * 5, - axeHeight, axeScale, rotation)
    };
    axeImg.src = axeImageSrc
}