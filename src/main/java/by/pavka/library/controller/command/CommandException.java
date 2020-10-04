package by.pavka.library.controller.command;

public class CommandException extends Exception {
  public CommandException() {
  }

  public CommandException(String message) {
    super(message);
  }

  public CommandException(String message, Throwable cause) {
    super(message, cause);
  }
}
