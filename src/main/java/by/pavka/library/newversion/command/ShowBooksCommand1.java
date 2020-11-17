package by.pavka.library.newversion.command;

import by.pavka.library.OrderHolder;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Queue;

public class ShowBooksCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    Queue orders = OrderHolder.getInstance().getPreparedOrders();
    HttpSession session = request.getSession();
    session.setAttribute(PREPARED, orders);
    return new PageRouter(PageRouter.SHOW_BOOKS);
  }
}
