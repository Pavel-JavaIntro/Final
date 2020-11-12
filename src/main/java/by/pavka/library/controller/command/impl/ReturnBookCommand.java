package by.pavka.library.controller.command.impl;

import by.pavka.library.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class ReturnBookCommand implements ActionCommand {
  private static final String NEXT = "/jsp/return_books.jsp";

  @Override
  public void execute(HttpServletRequest request) {
    request.getSession().setAttribute(PAGE, NEXT);
  }
}
