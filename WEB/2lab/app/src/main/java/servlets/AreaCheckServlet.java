package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import beans.UserData;
import beans.UserDataList;

import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Math.random;
import utils.ValuesCheck;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AreaCheckServlet extends HttpServlet {
    // Requesting and printing the output
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryString = req.getQueryString();
        if (queryString != null) {
            long nowTime = System.nanoTime();
            String[] parametersPairs = queryString.split("&");
            Map<String, String> parametersMap = new HashMap<String, String>();
            for (String parametersPair : parametersPairs) {
                String[] parameter = parametersPair.split("=");
                parametersMap.put(parameter[0], parameter[1].length() > 0 ? parameter[1] : "");
            }
            String xStr = parametersMap.get("xVal");
            String yStr = parametersMap.get("yVal");
            String rStr = parametersMap.get("rVal");

            ValuesCheck valuesCheckObj = new ValuesCheck();

            if (valuesCheckObj.xyrCheck(xStr, yStr, rStr)) {
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
                
                String pointColor = "rgb(" + round(random() * 255) + ", " + round(random() * 255) + ", " + round(random() * 255) + ")";

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();  
                String currentTime = dtf.format(now);  

                Long executionTime = System.nanoTime() - nowTime;

                // form UserData object
                UserData userData = new UserData(xVal, yVal, rVal, isHit, currentTime, executionTime.toString(), pointColor);
                userDataList.getUserDataList().add(userData);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
