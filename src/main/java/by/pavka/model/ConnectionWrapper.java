package by.pavka.model;

import by.pavka.model.dao.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionWrapper {
  private Connection connection;

  public ConnectionWrapper() throws DaoException {
    connection = DBConnectionPool.getInstance().obtainConnection();
    if (connection == null) {
      throw new DaoException("ConnectionWrapper cannot get connection");
    }
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
    DBConnectionPool.getInstance().releaseConnection(connection);
  }
}
