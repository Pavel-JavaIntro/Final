package by.pavka.library.model;

public class LibraryModelException extends RuntimeException {
  public LibraryModelException() {}

  public LibraryModelException(String message) {
    super(message);
  }

  public LibraryModelException(String message, Throwable cause) {
    super(message, cause);
  }
}
