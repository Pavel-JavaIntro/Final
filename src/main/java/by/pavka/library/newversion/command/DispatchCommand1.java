package by.pavka.library.newversion.command;

import by.pavka.library.BookOrder;
import by.pavka.library.ConfigurationManager;
import by.pavka.library.OrderHolder;
import by.pavka.library.entity.EditionInfo;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.LibraryService;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Queue;

public class DispatchCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    PageRouter pageRouter = new PageRouter();
    LibraryService service = LibraryService.getInstance();
    OrderHolder orderHolder = OrderHolder.getInstance();
    Queue<BookOrder> preparedOrders = orderHolder.getPreparedOrders();
    int bookId = 0;
    if (request.getParameter(BOOK) != null) {
      bookId = Integer.parseInt(request.getParameter(BOOK));
    }
    for (BookOrder bookOrder : preparedOrders) {
      for (EditionInfo editionInfo : bookOrder.getEditionInfoSet()) {
        if (editionInfo.getBook() != null && editionInfo.getBook().getId() == bookId) {
          BookOrder dispatchedOrder = bookOrder.passBook(editionInfo);
          orderHolder.fulfillOrder(dispatchedOrder);
          try {
            service.fulfillOrder(dispatchedOrder);
          } catch (ServiceException e) {
            pageRouter.setPage(PageRouter.ERROR);
            LOGGER.error("DispatchCommand hasn't completed");
          }
        }
      }
    }
    return pageRouter;
  }
}
