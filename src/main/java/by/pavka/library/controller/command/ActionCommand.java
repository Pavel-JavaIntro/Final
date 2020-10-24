package by.pavka.library.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
  public static final Logger LOGGER = LogManager.getLogger(ActionCommand.class);
  public static final String PAGE = "page";

  void execute(HttpServletRequest request);
}
