let canvas = document.getElementById("canvas"),
    ctx = canvas.getContext('2d');


class Point {
    constructor(x, y, r, imgSrc, offsetX, offsetY, scale, rotationAngle) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.imgSrc = imgSrc;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.scale = scale;
        this.rotationAngle = rotationAngle;
    }
}

let pointArray = []


// function drawPersistantRotatedPoint(xValue, yValue, rValue, img, offsetX, offsetY, scale, rotationAngle, ctx1) {
//     let xOnCanvas = canvas.width / 2 + canvas.width / 3 * (xValue / rValue)
//     let yOnCanvas = canvas.width / 2 - canvas.width / 3 * (yValue / rValue)

//     ctx1.translate(xOnCanvas, yOnCanvas); // move to point

//     ctx1.setTransform(scale, 0, 0, scale, xOnCanvas, yOnCanvas); // sets scale and origin

//     ctx1.rotate(rotationAngle);
//     ctx1.drawImage(img, -img.width / 2 - offsetX, -img.height / 2 - offsetY);
//     ctx1.save()
// }

function drawAnimatedRotatedPoint(xValue, yValue, rValue, img, offsetX, offsetY, scale, rotationAngle, ctx) {
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
    // $("#canvas").css("display", "block")
    // $("#canvasWithAxes").css("display", "none")

    let xValueRotate = xValue - rValue * 3

    let axeImg = new Image()
    let axeHeight = 40

    let fissureImg = new Image()
    let fissureHeight = 25
    let fissureScale = fissureHeight / fissureImg.height

    fissureScale = 0.1

    let frameCount = 80
    let frameRestCount = frameCount
    let angle = 0

    setTimeout(draw)

    function draw() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        angle = (Math.random() * (23 - 2) + 2 + angle) % 360
        let axeScale = axeHeight / axeImg.height
        let rotation = angle * Math.PI / 180
        axeImg.onload = function () {
            drawAnimatedRotatedPoint(xValueRotate, yValue, rValue, axeImg, axeHeight * 5, - axeHeight, axeScale, rotation, ctx)
        };
        axeImg.src = axeImageSrc

        frameRestCount--
        xValueRotate = xValueRotate + rValue / frameCount * 3
        if (frameRestCount != 0) {
            setTimeout(draw, 10)
        } else {
            // $("#canvas").css("display", "none")
            // $("#canvasWithAxes").css("display", "block")
            let canvasWithAxes = document.getElementById("canvasWithAxes"),
                ctxWithAxes = canvasWithAxes.getContext('2d');
            fissureImg.onload = function () {
                drawAnimatedRotatedPoint(xValue, yValue, rValue, fissureImg, 0, 0, fissureScale, rotation, ctxWithAxes)
                // pointArray.push(new Point(xValue, yValue, rValue, fissureImageSrc, 0, 0, fissureScale, rotation))
                axeImg.onload = function () {
                    drawAnimatedRotatedPoint(xValue, yValue, rValue, axeImg, axeHeight * 5, - axeHeight, axeScale, rotation, ctxWithAxes)
                    pointArray.push(new Point(xValue, yValue, rValue, axeImageSrc, axeHeight * 5, - axeHeight, axeScale, rotation))
                };
                axeImg.src = axeImageSrc
            };
            fissureImg.src = fissureImageSrc

            console.log("saved")
        }
    }

    // if (!isHit) {
    //     axeImg.onload = function () {
    //         drawAnimatedRotatedPoint(xValue, yValue, rValue, axeImg, axeHeight * 5, - axeHeight, axeScale, rotation)
    //         pointArray.push(new Point(xValue, yValue, rValue, axeImageSrc, axeHeight * 5, - axeHeight, axeScale, rotation))
    //     };
    //     axeImg.src = axeImageSrc
    // } else {

    // }
}