package by.pavka.library.controller.command.impl;

import by.pavka.library.OrderHolder;
import by.pavka.library.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Queue;

public class ShowBooksCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    Queue orders = OrderHolder.getInstance().getPreparedOrders();
    HttpSession session = request.getSession();
    session.setAttribute("prepared", orders);
    session.setAttribute(PAGE, "/jsp/show_books.jsp");
  }
}
