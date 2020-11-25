package by.pavka.library.newversion.command;

import by.pavka.library.BookOrder;
import by.pavka.library.ConfigurationManager;
import by.pavka.library.OrderHolder;
import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.client.AppClient;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.LibraryService;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OrderBookCommand1 implements Command1 {
  private static final String UNSELECT = "unselect";

  @Override
  public PageRouter execute(HttpServletRequest request) {
    PageRouter pageRouter = new PageRouter();
    HttpSession session = request.getSession();
    AppClient client = (AppClient)session.getAttribute(SESSION_ATTRIBUTE_CLIENT);
    String action = request.getParameter(ORDER);
    if(action.equals(UNSELECT)) {
      int editionId = 0;
      if (request.getParameter(BOOK) != null) {
        editionId = Integer.parseInt(request.getParameter(BOOK));
      }
      for (EditionInfo ei : client.getEditionInfos()) {
        if (ei.getEdition().getId() == editionId) {
          client.removeEditionInfo(ei);
          break;
        }
      }
      return pageRouter;
    } else {
      BookOrder bookOrder = new BookOrder(client);
      OrderHolder.getInstance().addOrder(bookOrder);
      LibraryService service = LibraryService.getInstance();
      try {
        service.orderBook(bookOrder);
        pageRouter.setPage(PageRouter.RESERVE);
      } catch (ServiceException e) {
        pageRouter.setPage(PageRouter.ERROR);
        LOGGER.error("OrderBookCommand hasn't completed");
      }
      return pageRouter;
    }
  }
}
