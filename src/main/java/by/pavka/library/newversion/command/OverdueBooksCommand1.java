package by.pavka.library.newversion.command;

import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.LibraryService;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OverdueBooksCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    PageRouter pageRouter = new PageRouter(PageRouter.OVERDUE_BOOKS);
    HttpSession session = request.getSession();
    session.removeAttribute(OVERDUE);
    LibraryService service = LibraryService.getInstance();
    LocalDate date = LocalDate.now();
    List<Book> onHandsBooks = null;
    List<Book> overdueBooks = new ArrayList<>();
    try {
      onHandsBooks = service.findDeskBooksOnHands();
      System.out.println(onHandsBooks);
      System.out.println(onHandsBooks.size());
      for (Book book : onHandsBooks) {
        Date dueSqlDate = (Date)book.fieldForName(Book.DUE_DATE).getValue();
        if (dueSqlDate != null) {
          LocalDate dueDate = dueSqlDate.toLocalDate();
          if (date.isAfter(dueDate)) {
            overdueBooks.add(book);
          }
        }
      }
      session.setAttribute(OVERDUE, overdueBooks);
    } catch (ServiceException | LibraryEntityException e) {
      pageRouter.setPage(PageRouter.ERROR);
      LOGGER.error("OverdueBooksCommand hasn't completed");
    }
    return pageRouter;
  }
}
