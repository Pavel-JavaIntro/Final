package by.pavka.library.controller.command.impl;

import by.pavka.library.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class BasketCommand implements ActionCommand {
  public static final String DESTINATION = "/jsp/order.jsp";

  @Override
  public void execute(HttpServletRequest request) {
    request.getSession().setAttribute(PAGE, DESTINATION);
  }
}
