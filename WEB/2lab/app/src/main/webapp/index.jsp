<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter,java.util.List" %>
<%@ page import="model.UserDataList,model.UserData" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Reshetov Semen P3206 WEB 2lab</title>
    <meta name="description" content="Reshetov Semen P3206 WEB 2lab">
    <meta name="author" content="Reshetov Semen">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Nunito+Sans:opsz@6..12&family=Poppins:wght@400;500;600;700&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="src/css/styles.css">
</head>

<body>
    <header>
        <div class="introduction">
            <h3>Second web laboratory</h3>
            <strong>Reshetov Semen, P3206. Variant: 1710</strong>
        </div>
        <div class="links">
            <form action="https://se.ifmo.ru/courses/web" target="_blank">
                <button class="nav-button">
                    SE VITMO
                </button>
            </form>
            <form action="https://github.com/rsp243" target="_blank">
                <button class="nav-button">
                    Github account
                </button>
            </form>
            <form action="https://t.me/rsp243" target="_blank">
                <button class="nav-button">
                    Telegram account
                </button>
            </form>
        </div>
    </header>

    <div class="main-container">
        <div class="canvas-and-selection">
            <canvas id="canvas" width="300" height="300"></canvas>
            <form action="controller" method="GET">    
                <div class="params-selection">
                    <div class="Xselection">
                        <p>Choose X value</p>
                        <%-- <p class="X-error-message error-message">X value have to be whole number between -3 and 5
                            (including)</p> --%>
                        <% for (int i = -3; i <= 5; i++) { %>
                            <input type="radio" id="contactChoice<%=i%>" name="xVal" value="<%=i%>" required />
                            <label for="contactChoice<%=i%>"><%=i%></label>
                        <% } %>
                    </div>
                    <div class="Yselection">
                        <p>Choose Y value</p>
                        <p class="Y-error-message error-message">Y value have to be float number between -3 and 5 (not
                            including)</p>
                        <input type="text" class="Yselection-text" id="Yselection" name="yVal" placeholder="Y value" maxlength="15" required />
                    </div>
                    <div class="Rselection">
                        <p>Choose R value</p>
                            <p class="R-error-message error-message">R value have to be float number between 1 and 3 (including)
                        </p>
                        <div class="checkbox-block">
                            <% for (float i = 1; i <= 3; i = i + (float) 0.5) { %>
                                <input type="checkbox" id="Rselection" name="rVal" value="<%=i%>" required/>
                                <label for="Rselection<%=i%>"><%=i%></label>
                            <% } %>
                        </div>
                    </div>
                </div>
            <div class="btn-block">
                <input type="submit" class="control-btn btn-process" value="Throw An Axe">
                <button class="control-btn btn-clear">Clear table</button>
            </div>
            </form>
        </div>
            <div class="data-table">
            <table cellspacing="1" cellpadding="10" width="100%">

                <thead>
                    <tr>
                        <th scope="col">X Value</th>
                        <th scope="col">Y Value</th>
                        <th scope="col">R Value</th>
                        <th scope="col">Hit/Miss</th>
                        <th scope="col">Event time</th>
                        <th scope="col">Execution time, ns</th>
                    </tr>
                </thead>
                <tbody class="table-body">
                    <% UserDataList userDataListObj = (UserDataList) getServletContext().getAttribute("UserDataList"); 
                    if (userDataListObj != null) { 
                        List<UserData> userDataList = userDataListObj.getUserDataList();
                        for (int i = 0; i < (userDataList == null ? 0 : userDataList.size()); i++) { %>
                            <tr>
                                <td class="xTable" scope="row"><%=userDataList.get(i).getxVal()%></td>
                                <td class="yTable" scope="row"><%=userDataList.get(i).getyVal()%></td>
                                <td class="rTable" scope="row"><%=userDataList.get(i).getrVal()%></td>
                                <td scope="row"><%=userDataList.get(i).isHit() ? "HIT" : "MISS" %></td>
                                <td scope="row"><%=userDataList.get(i).getCurrentTime()%></td>
                                <td scope="row"><%=userDataList.get(i).getExecutionTime()%></td>
                            </tr>
                        <% }
                    } %>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"
        integrity="sha256-lSjKY0/srUM9BE3dPm+c4fBo1dky2v27Gdjm2uoZaL0=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        function drawBeginnigGraph() {
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
    </script>
    <script type="module" src="src/js/canvas.js"></script>
    <script type="module" src="src/js/validation.js"></script>
    <script type="module" src="src/js/table.js"></script>
    <script type="module" src="src/js/btn-events.js"></script>
    <script type="module" src="src/js/checkbox-limit.js"></script>
    <script type="text/javascript">
    function drawPoint(xValue, yValue, rValue) {
        let canvas = document.getElementById("canvas"),
            ctx = canvas.getContext('2d');

        let img = new Image() 

        let imgHeight = 25
        let imgWidth = 25

        let xOnCanvas = canvas.width / 2 + canvas.width / 3 * (xValue / rValue) - imgWidth / 1.3
        let yOnCanvas = canvas.width / 2 - canvas.width / 3 * (yValue / rValue) - imgHeight / 2

        img.onload = function() {
            ctx.drawImage(img, xOnCanvas, yOnCanvas, imgWidth, imgHeight);
		};
        img.src = "src/img/axe.png" 
    }

    function setPreviousRValue(rValue) {
        let checkboxes = $('input[type="checkbox"]');
        $('input[type="checkbox"][value="' + rValue.toFixed(1) + '"]').prop('checked', true)
        checkboxes.filter(':not(:checked)').prop('disabled', true);
    }
    </script>
    <script type="text/javascript">
        drawBeginnigGraph()
        <% if (userDataListObj != null) {
            List<UserData> userDataList = userDataListObj.getUserDataList();
            if (userDataList != null && userDataList.size() > 0) {
                float rShowingValue = userDataList.get(userDataList.size() - 1).getrVal(); %>
                setPreviousRValue(<%= rShowingValue %>);
                <% int index = userDataList.size() - 1;
                while (index >= 0 && userDataList.get(index).getrVal() == rShowingValue) { %>
                    drawPoint(<%= userDataList.get(index).getxVal() %>, <%= userDataList.get(index).getyVal() %>,
                                <%= userDataList.get(index).getrVal() %>);
                    <% index--;
                }
            }
        } %>
    </script>

</body>

</html>⏎ 