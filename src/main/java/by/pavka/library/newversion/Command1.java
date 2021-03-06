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
  public static final String APP_ATTRIBUTE_EMAIL = "email";
  public static final String COMMAND = "command";
  public static final String WRONG_ACTION = "wrongAction";
  public static final String SURNAME = "surname";
  public static final String NAME = "name";
  public static final String PASSWORD = "password";
  public static final String ERROR_LOGIN_PASS = "errorLoginPassMessage";
  public static final String TITLE = "title";
  public static final String AUTHOR = "author";
  public static final String EDITIONS = "editions";
  public static final String LANGUAGE = "lan";
  public static final String EDITION = "edition";
  public static final String DESK = "desk";
  public static final String ORDER = "order";
  public static final String BOOK = "book";
  public static final String ORDERS = "orders";
  public static final String PREPARATION = "preparation";
  public static final String PREPARED = "prepared";
  public static final String CODE = "code";
  public static final String ADDITION = "addition";
  public static final String DECOMMISSION = "decommission";
  public static final String BOOK_LOCATION = "booklocation";
  public static final String BOOK_TITLE = "booktitle";
  public static final String BOOK_YEAR = "bookyear";
  public static final String GENRE = "genre";
  public static final String DECOM = "decom";
  public static final String USER_SURNAME = "surname";
  public static final String USER_NAME = "name";
  public static final String USERS = "users";
  public static final String USER_ADDITION = "user_addition";
  public static final String USER_ADDRESS = "address";
  public static final String USER_PHONE = "phone";
  public static final String USER_EMAIL = "email";
  public static final String USER_ROLE = "role";
  public static final String USER = "user";
  public static final String STATUS = "status";
  public static final String RETURN = "return";
  public static final String RETURNING = "returning";
  public static final String RESULT = "result";
  public static final String OVERDUE = "overdue";

  default PageRouter executeCommand(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String) session.getAttribute(SESSION_ATTRIBUTE_PAGE);
    LOGGER.info("Current Page is " + page);
    PageRouter pageRouter = execute(request);
    String nextPage = pageRouter.getPage();
    if (nextPage.isEmpty()) {
      pageRouter.setPage(page);
    } else {
      session.setAttribute(SESSION_ATTRIBUTE_PAGE, nextPage);
    }
    LOGGER.info("Next Page is " + (String) session.getAttribute(SESSION_ATTRIBUTE_PAGE));
    return pageRouter;
  }

  PageRouter execute(HttpServletRequest request);
}
