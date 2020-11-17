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

public class PrepareBookCommand1 implements Command1 {
  public static final String PREPARE = "prepare";

  @Override
  public PageRouter execute(HttpServletRequest request) {
    PageRouter pageRouter = new PageRouter();
    String action = request.getParameter(PREPARATION);
    OrderHolder orderHolder = OrderHolder.getInstance();
    Queue<BookOrder> placedOrders = orderHolder.getPlacedOrders();
    int bookId = Integer.parseInt(request.getParameter(BOOK));
    LibraryService service = LibraryService.getInstance();
    for (BookOrder bookOrder : placedOrders) {
      for (EditionInfo editionInfo : bookOrder.getEditionInfoSet()) {
        if (editionInfo.getBook() != null && editionInfo.getBook().getId() == bookId) {
          BookOrder passedOrder = bookOrder.passBook(editionInfo);
          if (action.equals(PREPARE)) {
            orderHolder.prepareOrder(passedOrder);
            try {
              service.prepareOrder(passedOrder);
            } catch (ServiceException e) {
              pageRouter.setPage(PageRouter.ERROR);
              LOGGER.error("PrepareBookCommand hasn't completed");
            }
          } else {
            orderHolder.denyOrder(passedOrder);
            try {
              service.denyOrder(passedOrder);
            } catch (ServiceException e) {
              pageRouter.setPage(PageRouter.ERROR);
              LOGGER.error("PrepareBookCommand hasn't completed");
            }
          }
        }
      }
    }
    return pageRouter;
  }
}
