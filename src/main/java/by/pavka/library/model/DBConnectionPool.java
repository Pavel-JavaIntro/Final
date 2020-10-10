package by.pavka.library.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class DBConnectionPool {
  private static final DBConnectionPool instance = new DBConnectionPool();
  private static final int TIMEOUT = 3;

  private BlockingQueue<Connection> connections;
  private BlockingQueue<Connection> usedConnections;
  private int maxSize;
  private String url;
  private String login;
  private String password;

  private DBConnectionPool() {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    String driver = resourceBundle.getString("driver");
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      throw new LibraryFatalException("Database Driver not found", e);
    }
    url = resourceBundle.getString("url");
    login = resourceBundle.getString("user");
    password = resourceBundle.getString("pass");
    String num = resourceBundle.getString("connections");
    String maxNum = resourceBundle.getString("max_connections");
    int connectionsNumber;
    try {
      connectionsNumber = Integer.parseInt(num);
    } catch (NumberFormatException e) {
      // TODO Log it
      connectionsNumber = 1;
    }
    try {
      maxSize = Integer.parseInt(maxNum);
    } catch (NumberFormatException e) {
      // TODO Log it
      maxSize = connectionsNumber;
    }
    connections = new ArrayBlockingQueue<>(maxSize);
    usedConnections = new ArrayBlockingQueue<>(maxSize);
    for (int i = 0; i < connectionsNumber; i++) {
      connections.add(createConnection(url, login, password));
    }
  }

  public static DBConnectionPool getInstance() {
    return instance;
  }

  private Connection createConnection(String url, String login, String password) {
    try {
      return DriverManager.getConnection(url, login, password);
    } catch (SQLException e) {
      throw new LibraryFatalException("Database Connection not created", e);
    }
  }

  private synchronized boolean addConnection() {
    if (connections.size() >= maxSize) {
      return false;
    }
    try {
      connections.add(DriverManager.getConnection(url, login, password));
    } catch (SQLException e) {
      // TODO Log it;
      return false;
    }
    return true;
  }

  public Connection obtainConnection() {
    // TODO
    Connection connection = pickConnection();
    if (connection == null) {
      if (addConnection()) {
        connection = pickConnection();
      }
    }
    if (connection != null) {
      usedConnections.offer(connection);
    }
    return connection;
  }

  private Connection pickConnection() {
    Connection connection = null;
    try {
      connection = connections.poll(TIMEOUT, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return connection;
  }

  public boolean releaseConnection(Connection connection) {
    if (connection != null) {
      connections.offer(connection);
      return usedConnections.remove(connection);
    }
    return false;
  }
}
