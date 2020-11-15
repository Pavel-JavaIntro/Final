package by.pavka.library.newversion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface Command1 {
  public static final Logger LOGGER = LogManager.getLogger(Command1.class);
  public static final String SESSION_ATTRIBUTE_CLIENT = "client";
  public static final String SESSION_ATTRIBUTE_LANGUAGE = "lan";
  public static final String SESSION_ATTRIBUTE_PAGE = "page";
  public static final String APP_ATTRIBUTE_BOOKS = "books";
  public static final String APP_ATTRIBUTE_USERS = "users";

  default PageRouter executeCommand(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute(SESSION_ATTRIBUTE_PAGE);
    LOGGER.info("Current Page is " + page);
    PageRouter pageRouter = execute(request);
    String nextPage = pageRouter.getPage();
    if (nextPage.isEmpty()) {
      pageRouter.setPage(page);
    } else {
      session.setAttribute(SESSION_ATTRIBUTE_PAGE, nextPage);
    }
    LOGGER.info("Next Page is " + (String)session.getAttribute(SESSION_ATTRIBUTE_PAGE));
    return pageRouter;
  }

  PageRouter execute(HttpServletRequest request);
}
