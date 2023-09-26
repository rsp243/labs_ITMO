package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AreaCheckServlet extends HttpServlet {
    private String output;

    // Initializing servlet
    public void init() throws ServletException {

    }

    // Requesting and printing the output
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

    }

    public void destroy() {

    }
}
