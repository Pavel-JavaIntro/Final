package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DecommissionBookCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String) session.getAttribute(PAGE);
    String bookS = request.getParameter("decom");
    System.out.println("BookS = " + bookS);
    int bookId = Integer.parseInt(bookS);
    WelcomeService welcomeService = WelcomeService.getInstance();
    try {
      welcomeService.decommissionBook(bookId);
    } catch (ServiceException e) {
      page = ConfigurationManager.getProperty("error");
      LOGGER.error("DecommissionBookCommand hasn't completed");
    }
    session.setAttribute(PAGE, page);
  }
}
