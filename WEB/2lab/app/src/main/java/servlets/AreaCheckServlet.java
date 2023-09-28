package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.lang.System;
import static java.lang.Math.pow;
import utils.ValuesCheck;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AreaCheckServlet extends HttpServlet {
    // Requesting and printing the output
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String queryString = req.getQueryString();
        if (queryString != null) {
            Calendar calendar = Calendar.getInstance();
            long nowTime = calendar.getTimeInMillis();
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
                long executionTime = calendar.getTimeInMillis() - nowTime;
            }
        }
    }
}
