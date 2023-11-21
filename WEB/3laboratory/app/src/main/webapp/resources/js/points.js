let canvas1 = document.getElementById("canvas")
ctx1 = canvas1.getContext('2d');

// function drawPersistantRotatedPoint(xValue, yValue, rValue, img, offsetX, offsetY, scale, rotationAngle, ctx1) {
//     let xOnCanvas = canvas.width / 2 + canvas.width / 3 * (xValue / rValue)
//     let yOnCanvas = canvas.width / 2 - canvas.width / 3 * (yValue / rValue)

//     ctx1.translate(xOnCanvas, yOnCanvas); // move to point

//     ctx1.setTransform(scale, 0, 0, scale, xOnCanvas, yOnCanvas); // sets scale and origin

//     ctx1.rotate(rotationAngle);
//     ctx1.drawImage(img, -img.width / 2 - offsetX, -img.height / 2 - offsetY);
//     ctx1.save()
// }

function drawAnimatedRotatedPoint(xValue, yValue, rValue, imgHeight, imageSrc, offsetX, offsetY, rotationAngle, ctx) {
    let img = new Image()
    img.onload = function () {
        ctx1.clearRect(0, 0, canvas.width, canvas.height);
        let xOnCanvas = canvas.width / 2 + canvas.width / 3 * (xValue / rValue)
        let yOnCanvas = canvas.width / 2 - canvas.width / 3 * (yValue / rValue)

        let imageScale = imgHeight / img.height
        ctx.save()
        ctx.translate(xOnCanvas, yOnCanvas); // move to point

        ctx.setTransform(imageScale, 0, 0, imageScale, xOnCanvas, yOnCanvas); // sets scale and origin

        ctx.rotate(rotationAngle);
        ctx.drawImage(img, -img.width / 2 - offsetX, -img.height / 2 - offsetY);
        ctx.restore()
    };
    img.src = imageSrc
}

function drawPoint(xValue, yValue, rValue, isHit, imageSrc, rotationAngle, canvasIDstr, isAxeAnimated) {
    let axeHeight = 40 // need to fix
    let fissureHeight = 25 // need to fix

    let canvas = document.getElementById(canvasIDstr),
        ctx = canvas.getContext('2d');

    if (isAxeAnimated) {
        drawAnimatedRotatedPoint(xValue, yValue, rValue, axeHeight, imageSrc, axeHeight * 5, - axeHeight, rotationAngle, ctx)
    } else {
        drawAnimatedRotatedPoint(xValue, yValue, rValue, fissureHeight, imageSrc, 0, 0, rotationAngle, ctx)
    }
}

function drawIsHitPoint(xValue, yValue, rValue, isHit) {
    let rValueScale = 3
    let xValueRotate = xValue - rValue * rValueScale

    let frameCount = 80
    let frameRestCount = frameCount
    let angle = 0
    let rotation


    setTimeout(drawFhrow)

    function drawFhrow() {

        angle = (Math.random() * (23 - 2) + 2 + angle) % 360
        rotation = angle * Math.PI / 180

        drawPoint(xValueRotate, yValue, rValue, isHit, axeImageSrc, rotation, "canvas", true)

        frameRestCount--
        xValueRotate = xValueRotate + rValue / frameCount * rValueScale
        if (frameRestCount != 0) {
            setTimeout(drawFhrow, 10)
        } else {
            drawPoint(xValue, yValue, rValue, isHit, fissureImageSrc, rotation, "canvasWithAxes", false)
            drawIsHit()
        }
    }

    function drawIsHit() {
        let yValueRotate = yValue
        if (isHit) {
            drawPoint(xValueRotate, yValue, rValue, isHit, axeImageSrc, rotation, "canvasWithAxes", true)
        } else {
            frameRestCount = frameCount
            setTimeout(drawNotHit)
        }
        function drawNotHit() {
            xValueRotate -= rValue / frameCount * rValueScale / 2
            yValueRotate -= ((xValue - xValueRotate) ** 2) / frameCount * 4

            angle = (Math.random() * (23 - 2) + 2 + angle) % 360
            let rotation = angle * Math.PI / 180
            drawPoint(xValueRotate, yValueRotate, rValue, isHit, axeImageSrc, rotation, "canvas", true)

            frameRestCount--
            if (frameRestCount != 0) {
                setTimeout(drawNotHit, 10)
            }
        }

    }
}