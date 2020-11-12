package by.pavka.library.controller.command.impl;

import by.pavka.library.BookOrder;
import by.pavka.library.ConfigurationManager;
import by.pavka.library.OrderHolder;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.client.AppClient;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OrderBookCommand implements ActionCommand {
  private static final String UNSELECT = "unselect";

  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = "/jsp/reserve.jsp";
    AppClient client = (AppClient)session.getAttribute("client");
    String action = request.getParameter("order");
    if(action.equals(UNSELECT)) {

      int editionId = 0;
      if (request.getParameter("book") != null) {
        editionId = Integer.parseInt(request.getParameter("book"));
      }
      for (EditionInfo ei : client.getEditionInfos()) {
        if (ei.getEdition().getId() == editionId) {
          client.removeEditionInfo(ei);
          break;
        }
      }
    } else {
      BookOrder bookOrder = new BookOrder(client);
      OrderHolder.getInstance().addOrder(bookOrder);
      WelcomeService welcomeService = WelcomeService.getInstance();
      try {
        welcomeService.orderBook(bookOrder);
      } catch (ServiceException e) {
        page = ConfigurationManager.getProperty("error");
        LOGGER.error("OrderBookCommand hasn't completed");
      }
      session.setAttribute(PAGE, page);
    }
  }
}
