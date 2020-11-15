package by.pavka.library.newversion;

import by.pavka.library.MessageManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.controller.command.CommandEnum;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory1 {
  public static Command1 defineCommand(HttpServletRequest request) {
    Command1 command = CommandEnum1.EMPTY.getCommand();
    String action = request.getParameter(ParameterManager.COMMAND);
    if (action == null || action.isEmpty()) {
      return command;
    }
    try {
      CommandEnum1 commandEnum = CommandEnum1.valueOf(action.toUpperCase());
      command = commandEnum.getCommand();
    } catch (IllegalArgumentException e) {
      request.setAttribute(
          ParameterManager.WRONG_ACTION,
          action + MessageManager.getProperty("message.wrongaction"));
    }
    return command;
  }
}
