package by.pavka.library;

import by.pavka.library.model.DBConnectionPool;
import by.pavka.library.model.mapper.ConstantManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ResourceBundle;


public class LibraryStarter implements ServletContextListener {

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    DBConnectionPool.getInstance();
    ConstantManager.getLocationById(0);
    ServletContext context = servletContextEvent.getServletContext();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    String email = resourceBundle.getString("email");
    context.setAttribute("email", email);
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    //TODO
  }
}
