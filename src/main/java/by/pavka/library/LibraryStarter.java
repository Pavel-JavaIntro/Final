package by.pavka.library;

import by.pavka.library.model.DBConnectionPool;
import by.pavka.library.model.mapper.ConstantManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Locale;
import java.util.ResourceBundle;


public class LibraryStarter implements ServletContextListener {
  private static final Logger logger = LogManager.getLogger(LibraryStarter.class.getName());

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    logger.info("STARTED");
    //Locale.setDefault(new Locale("ru"));
    //DBConnectionPool.getInstance();
    //ConstantManager.getLocationById(0);
    ServletContext context = servletContextEvent.getServletContext();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    String email = resourceBundle.getString("email");
    context.setAttribute("email", email);
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    DBConnectionPool.getInstance().unconnect();
  }
}
