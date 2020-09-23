package by.pavka;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ResourceBundle;

public class LibraryStarter implements ServletContextListener {

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    ServletContext context = servletContextEvent.getServletContext();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    String num = resourceBundle.getString("connections");
    context.setAttribute("conn", num);
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {

  }
}
