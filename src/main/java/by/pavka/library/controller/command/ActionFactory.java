package by.pavka.library.controller.command;

import by.pavka.library.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
  public ActionCommand defineCommand(HttpServletRequest request) {
    ActionCommand command = CommandEnum.EMPTY.getCommand();
    String action = request.getParameter("command");
    if (action == null || action.isEmpty()) {
      return command;
    }
    try {
      CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
      command = commandEnum.getCommand();
    } catch (IllegalArgumentException e) {
      request.setAttribute("wrongAction", action + MessageManager.getProperty("wrongaction"));
    }
    return command;
  }
}
