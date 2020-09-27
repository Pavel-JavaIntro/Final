package by.pavka;

import by.pavka.model.DBConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class LibraryStarter implements ServletContextListener {

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    DBConnectionPool.getInstance();
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {
  }
}
