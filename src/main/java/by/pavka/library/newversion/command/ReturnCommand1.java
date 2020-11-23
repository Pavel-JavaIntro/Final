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

public class ReturnCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    PageRouter pageRouter = new PageRouter();
    HttpSession session = request.getSession();
    int bookId = Integer.parseInt(request.getParameter(RETURN));
    LibraryService service = LibraryService.getInstance();
    Book book = null;
    try {
      book = service.findBookById(bookId);
    } catch (ServiceException e) {
      pageRouter.setPage(PageRouter.ERROR);
      LOGGER.error("ReturnCommand hasn't completed");
    }
    session.setAttribute(RETURNING, book);
    return pageRouter;
  }
}
