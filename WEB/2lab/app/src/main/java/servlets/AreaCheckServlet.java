package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Math.random;
import utils.ValuesCheck;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserData;
import model.UserDataList;

public class AreaCheckServlet extends HttpServlet {

    // Requesting and printing the output
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryString = req.getQueryString();
        if (queryString != null) {
            long nowTime = System.nanoTime();
            String xStr = "";
            String yStr = "";
            String rStr = "";
            try {
                String[] parametersPairs = queryString.split("&");
                Map<String, String> parametersMap = new HashMap<String, String>();
                for (String parametersPair : parametersPairs) {
                    String[] parameter = parametersPair.split("=");
                    parametersMap.put(parameter[0], parameter[1].length() > 0 ? parameter[1] : "");
                }
                xStr = parametersMap.get("xVal");
                yStr = parametersMap.get("yVal");
                rStr = parametersMap.get("rVal");
            } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
                resp.sendError(400, "Bad Request");
                return;
            }
            ValuesCheck valuesCheckObj = new ValuesCheck();
            if (!valuesCheckObj.xyrCheck(xStr, yStr, rStr)) {
                resp.sendError(400, "Parameters Are Not In Their Ranges");
                return;
            }
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            String contextPath = getServletContext().getContextPath();

            out.println(
                    """
                            <!doctype html>
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
                                    """);
            out.println("<link rel=\"stylesheet\" href=\"" + contextPath
                    + "/src/css/areaCheckStyles.css\"></head>");
            out.println(
                    """
                                <body>
                                    <header>
                                        <div class="introduction">
                                            <h3>First web laboratory</h3>
                                            <strong>Reshetov Semen, P3206. Variant: 1623</strong>
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

                            """);

            ServletContext servletContext = this.getServletContext();
            UserDataList userDataList = (UserDataList) servletContext.getAttribute("UserDataList");
            int xVal = Integer.parseInt(xStr);
            float yVal = Float.parseFloat(yStr);
            float rVal = Float.parseFloat(rStr);

            // triangle hit check
            boolean sector1Hit = xVal >= 0 && yVal >= 0 && xVal + yVal <= rVal;
            boolean sector2Hit = false;
            // 1/4 circle hit check
            boolean sector3Hit = xVal < 0 && yVal < 0 && pow(xVal, 2) + pow(yVal, 2) <= pow(rVal, 2);
            // square hit check
            boolean sector4Hit = xVal > 0 && yVal < 0 && xVal <= rVal && yVal <= rVal;
            boolean isHit = sector1Hit || sector2Hit || sector3Hit || sector4Hit;

            String pointColor = "rgb(" + round(random() * 255) + ", " + round(random() * 255) + ", "
                    + round(random() * 255) + ")";

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String currentTime = dtf.format(now);

            Long executionTime = System.nanoTime() - nowTime;

            // form UserData object
            UserData userData = new UserData(xVal, yVal, rVal, isHit, currentTime, executionTime.toString(),
                    pointColor);
            userDataList.getUserDataList().add(userData);

            out.println(
                    """
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
                                            <th scope="col">Color(RGB)</th>
                                        </tr>
                                    </thead>
                                    <tbody class="table-body">
                            """);
            out.println("<tr><td scope='row'>" + userData.getxVal() + "</td>");
            out.println("<td scope='row'>" + userData.getyVal() + "</td>");
            out.println("<td scope='row'>" + userData.getrVal() + "</td>");
            out.println("<td scope='row'>" + (userData.isHit() ? "HIT" : "MISS") + "</td>");
            out.println("<td scope='row'>" + userData.getCurrentTime() + "</td>");
            out.println("<td scope='row'>" + userData.getExecutionTime() + "</td>");
            out.println("<td scope='row'>" + userData.getPointColor() + "</td></tr>");
            out.println("""
                                        </tbody>
                                        </table>
                                    </div>
                    """);
            out.println("<div class=\"btn-block\">");
            out.println("<a href=\"" + contextPath
                    + "\"><button class=\"control-btn\">Back To The Main Page</button></a>");
            out.println("""
                                </div>
                            </div>
                    """);
            out.println("""
                    </body>
                    </html>
                        """);
        }
    }
}
