package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String queryString = req.getQueryString();
        String forwardAddress = getServletContext().getContextPath();
        if (queryString != null) {
            try {
                String[] parametersPairs = queryString.split("&");
                Map<String, String> parametersMap = new HashMap<String, String>();
                for (String parametersPair : parametersPairs) {
                    String[] parameter = parametersPair.split("=");
                    parametersMap.put(parameter[0], parameter[1].length() > 0 ? parameter[1] : "");
                }
                String xStr = parametersMap.get("xVal");
                String yStr = parametersMap.get("yVal");
                String rStr = parametersMap.get("rVal");
                if (xStr != null && yStr != null && rStr != null) {
                    forwardAddress += "/v1/areacheck" ;
                }
            } catch(Exception exception) {
                log("The request is not valid", exception);
            }
        } 
        resp.sendRedirect(forwardAddress);
    }
}