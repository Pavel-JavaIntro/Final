package by.pavka.library.newversion.command;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.LibraryService;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FixReturnCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    PageRouter pageRouter = new PageRouter();
    Book book = (Book) session.getAttribute(RETURNING);
    LibraryService service = LibraryService.getInstance();
    if (book != null) {
      try {
        service.fixReturn(book);
        session.removeAttribute(RETURNING);
        session.setAttribute(RESULT, PageRouter.RESULT_SUCCESS);
      } catch (ServiceException e) {
        pageRouter.setPage(PageRouter.ERROR);
        LOGGER.error("FixReturnCommand hasn't completed");
      }
    }
    return pageRouter;
  }
}
