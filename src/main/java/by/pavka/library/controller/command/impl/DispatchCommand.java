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
import java.util.Queue;

public class DispatchCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute(PAGE);
    WelcomeService welcomeService = WelcomeService.getInstance();
    OrderHolder orderHolder = OrderHolder.getInstance();
    Queue<BookOrder> preparedOrders = orderHolder.getPreparedOrders();
    int bookId = 0;
    if (request.getParameter("book") != null) {
      bookId = Integer.parseInt(request.getParameter("book"));
    }
    for (BookOrder bookOrder : preparedOrders) {
      for (EditionInfo editionInfo : bookOrder.getEditionInfoSet()) {
        if (editionInfo.getBook() != null && editionInfo.getBook().getId() == bookId) {
          BookOrder dispatchedOrder = bookOrder.passBook(editionInfo);
          orderHolder.fulfillOrder(dispatchedOrder);
          try {
            welcomeService.fulfillOrder(dispatchedOrder);
          } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("error");
            LOGGER.error("DispatchCommand hasn't completed");
          }
          session.setAttribute(PAGE, page);
        }
      }
    }
  }
}
