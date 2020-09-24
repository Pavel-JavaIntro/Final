package by.pavka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionWrapper {
  private Connection connection;

  public ConnectionWrapper() {
    connection = LibraryConnectionPool.obtainConnection();
  }

  public Statement obtainStatement() throws DaoException {
    if (connection != null) {
      try {
        return connection.createStatement();
      } catch (SQLException e) {
        throw new DaoException("Not obtained statement", e);
      }
    }
    throw new DaoException("Connection is null");
  }

  public PreparedStatement obtainPreparedStatement(String sql) throws DaoException {
    if (connection != null) {
      try {
        return connection.prepareStatement(sql);
      } catch (SQLException e) {
        throw new DaoException("Not obtained statement", e);
      }
    }
    throw new DaoException("Connection is null");
  }

  public void closeStatement(Statement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        // This imitates logging
        //TODO
        System.out.println("Statement not closed");
        e.printStackTrace();
      }
    }
  }

  public void closeConnection() {
    LibraryConnectionPool.releaseConnection(connection);
  }
}
