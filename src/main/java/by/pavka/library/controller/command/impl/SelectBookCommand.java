package by.pavka.library.controller.command.impl;

import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.client.AppClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SelectBookCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    String id = request.getParameter("book");
    System.out.println("BOOK ID = " + id);
    AppClient client = (AppClient)request.getSession().getAttribute("client");
    client.addEdition(Integer.parseInt(id));
  }
}
