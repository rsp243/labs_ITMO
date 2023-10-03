package servlets;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserDataList;

public class ClearTableServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String forwardAddress = getServletContext().getContextPath();
        ServletContext ctx = getServletContext();
        ctx.setAttribute("UserDataList", new UserDataList());
        resp.sendRedirect(forwardAddress);
    }

}
