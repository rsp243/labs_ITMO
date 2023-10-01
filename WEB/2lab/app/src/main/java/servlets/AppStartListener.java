package servlets;

import beans.UserDataList;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppStartListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        UserDataList userDataListObj = new UserDataList();
        ctx.setAttribute("UserDataList", userDataListObj);
    }
    
}
