package by.pavka.library;

import by.pavka.library.model.DBConnectionPool;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.DBConnectorPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ResourceBundle;

public class LibraryStarter implements ServletContextListener {
  private static final Logger LOGGER = LogManager.getLogger(LibraryStarter.class.getName());

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    LOGGER.info("STARTED");
    DBConnectionPool.getInstance();
    DBConnectorPool.getInstance();
    ConstantManager.getLocationById(0);
    OrderHolder.getInstance();
    ServletContext context = servletContextEvent.getServletContext();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    String email = resourceBundle.getString("email");
    context.setAttribute(Command1.APP_ATTRIBUTE_EMAIL, email);
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    LOGGER.info("FINISHED");
    DBConnectionPool.getInstance().disconnect();
    DBConnectorPool.getInstance().disconnect();
  }
}
