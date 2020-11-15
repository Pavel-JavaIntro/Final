package by.pavka.library.newversion.command;

import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    return new PageRouter(PageRouter.INDEX);
  }

  @Override
  public PageRouter executeCommand(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute(SESSION_ATTRIBUTE_PAGE);
    LOGGER.info("Current Page is " + page);
    PageRouter pageRouter = execute(request);
    String nextPage = pageRouter.getPage();
    LOGGER.info("Next Page is " + nextPage);
    session.invalidate();
    return pageRouter;
  }
}
