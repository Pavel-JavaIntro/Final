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
import java.util.List;

public class FindCodeCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    PageRouter pageRouter = new PageRouter();
    String code = request.getParameter(CODE);
    LibraryService service = LibraryService.getInstance();
    HttpSession session = request.getSession();
    session.setAttribute(CODE, code);
    session.removeAttribute(DECOMMISSION);
    try {
      int editionId = service.editionIdByCode(code);
      if (editionId == 0) {
        session.setAttribute(ADDITION, PageRouter.EDITION_ADDITION);
      } else {
        List<Book> books = service.findBooksByEditionCode(code);
        session.setAttribute(ADDITION, PageRouter.BOOK_ADDITION);
        session.setAttribute(DECOMMISSION, books);}
    } catch (ServiceException e) {
      pageRouter.setPage(PageRouter.ERROR);
      LOGGER.error("FindCodeCommand hasn't completed");
    }
    return pageRouter;
  }
}
