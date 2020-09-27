package by.pavka.model;

public class DatabaseAccessException extends RuntimeException {
  public DatabaseAccessException(String message) {
    super(message);
  }

  public DatabaseAccessException(String message, Throwable cause) {
    super(message, cause);
  }
}
