package by.pavka.library.controller.command.impl;

import by.pavka.library.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserOptionsCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.setAttribute(PAGE, "/jsp/process_users.jsp");
  }
}
