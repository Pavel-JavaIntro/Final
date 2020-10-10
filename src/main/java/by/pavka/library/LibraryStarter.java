package by.pavka.library;

import by.pavka.library.model.DBConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ResourceBundle;


public class LibraryStarter implements ServletContextListener {

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    DBConnectionPool.getInstance();
    ServletContext context = servletContextEvent.getServletContext();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    String email = resourceBundle.getString("email");
    context.setAttribute("email", email);
    context.setAttribute("users", 3);
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    //TODO
  }
}
