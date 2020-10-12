package by.pavka.library.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
  public static final Logger logger = LogManager.getLogger(ActionCommand.class.getName());

  void execute(HttpServletRequest request, HttpServletResponse response);
}
