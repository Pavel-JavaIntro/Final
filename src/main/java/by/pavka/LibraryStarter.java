package by.pavka;

import by.pavka.model.DBConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ResourceBundle;


public class LibraryStarter implements ServletContextListener {

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    //DBConnectionPool.getInstance();
    ServletContext context = servletContextEvent.getServletContext();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    String email = resourceBundle.getString("email");
    context.setAttribute("email", email);
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {
  }
}
