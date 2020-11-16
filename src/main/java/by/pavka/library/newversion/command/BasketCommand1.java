package by.pavka.library.newversion.command;

import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BasketCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.removeAttribute(EDITIONS);
    return new PageRouter(PageRouter.ORDER);
  }
}
