package by.pavka;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LibraryConnectionPool implements ServletContextListener, ConnectionPool {
  //TODO refactor

  private static List<Connection> freeConnections = new ArrayList<Connection>();
  private static List<Connection> connectionsInUse = new ArrayList<Connection>();

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    ServletContext context = servletContextEvent.getServletContext();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    String url = resourceBundle.getString("url");
    String login = resourceBundle.getString("user");
    String password = resourceBundle.getString("pass");
    String num = resourceBundle.getString("connections");
    int connectionsNumber = Integer.parseInt(num);
    for (int i = 0; i < connectionsNumber; i++) {
      try {
        freeConnections.add(createConnection(url, login, password));
      } catch (SQLException throwables) {
        // TODO
        throwables.printStackTrace();
      }
    }
    context.setAttribute("conn", num);
  }

  private Connection createConnection(String url, String login, String password)
      throws SQLException {
    return DriverManager.getConnection(url, login, password);
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    try {
      for (Connection fcon : freeConnections) {
        fcon.close();
      }
      for (Connection ucon : connectionsInUse) {
        ucon.close();
      }
    } catch (SQLException throwables) {
      //TODO
      throwables.printStackTrace();
    }
  }

  public Connection obtainConnection() {
    Connection connection = freeConnections.remove(freeConnections.size() - 1);
    connectionsInUse.add(connection);
    return connection;
  }

  public boolean releaseConnection(Connection connection) {
    freeConnections.add(connection);
    return connectionsInUse.remove(connection);
  }
}
