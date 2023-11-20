let canvas = document.getElementById("canvas"),
    ctx = canvas.getContext('2d');

function drawPointWithHeightWeightXYOffset(xValue, yValue, rValue, img, offsetX, offsetY, scale, rotationAngle) {
    let xOnCanvas = canvas.width / 2 + canvas.width / 3 * (xValue / rValue)
    let yOnCanvas = canvas.width / 2 - canvas.width / 3 * (yValue / rValue)

    // let xOnCanvasOff = canvas.width / 2 + canvas.width / 3 * (xValue / rValue) + offsetX
    // let yOnCanvasOff = canvas.width / 2 - canvas.width / 3 * (yValue / rValue) + offsetY

    // let rotation = degrees * Math.PI / 180

    ctx.save()

    // ctx.clearRect(0, 0, 300, 300); // clear canvas
    ctx.translate(xOnCanvas, yOnCanvas); // move to point

    ctx.setTransform(scale, 0, 0, scale, xOnCanvas, yOnCanvas); // sets scale and origin

    ctx.rotate(rotationAngle);
    ctx.drawImage(img, -img.width / 2 - offsetX, -img.height / 2 - offsetY);
    ctx.restore()
}

function drawIsHitPoint(xValue, yValue, rValue, isHit) {

    let xValueRotate = xValue - rValue * 2.5

    let axeImg = new Image()
    let axeHeight = 40

    let fissureImg = new Image()
    let fissureHeight = 25
    let axeDegrees = Math.random() * 360

    let frameCount = 50
    let frameRestCount = frameCount
    let angle = 0
    
    setTimeout(draw)

    fissureImg.onload = function () {
        let fissureScale = fissureHeight / fissureImg.height

        let fissureDegrees = Math.random() * 360
        let rotation = fissureDegrees * Math.PI / 180
        drawPointWithHeightWeightXYOffset(xValue, yValue, rValue, fissureImg, 0, 0, fissureScale, rotation)
    };
    fissureImg.src = fissureImageSrc

    console.log("I'm here")
    
    axeImg.onload = function () {

        let axeScale = axeHeight / axeImg.height

        // axeScale = 1

        let rotation = axeDegrees * Math.PI / 180
        drawPointWithHeightWeightXYOffset(xValue, yValue, rValue, axeImg, axeHeight * 5, - axeHeight, axeScale, rotation)
    };
    axeImg.src = axeImageSrc

    ctx.save()

    function draw() {
        drawBeginnigGraph()

        axeImg.onload = function () {
            angle = (Math.random() * (23 - 2) + 2 + angle) % 360
            let axeScale = axeHeight / axeImg.height
            let rotation = angle * Math.PI / 180
            drawPointWithHeightWeightXYOffset(xValueRotate, yValue, rValue, axeImg, axeHeight * 5, - axeHeight, axeScale, rotation)
        }; 
        axeImg.src = axeImageSrc
    
        frameRestCount--
        xValueRotate = xValueRotate + rValue / frameCount * 2.5
        if (frameRestCount != 0) {
            setTimeout(draw, 10)
        }
    }
}