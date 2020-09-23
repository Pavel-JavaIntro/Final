package by.pavka;

import java.sql.Connection;

public interface ConnectionPool {
  Connection obtainConnection();
  boolean releaseConnection(Connection connection);
}
