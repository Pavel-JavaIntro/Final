package by.pavka.library.newversion.command;

import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BookOptionsCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.removeAttribute(ADDITION);
    session.removeAttribute(DECOMMISSION);
    session.removeAttribute(RESULT);
    return new PageRouter(PageRouter.PROCESS_BOOKS);
  }
}
