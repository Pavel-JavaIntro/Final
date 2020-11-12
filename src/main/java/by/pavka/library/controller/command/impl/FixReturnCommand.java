package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FixReturnCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute(PAGE);
    Book book = (Book)session.getAttribute("returning");
    WelcomeService welcomeService = WelcomeService.getInstance();
    if (book != null) {
      try {
        welcomeService.fixReturn(book);
      } catch (ServiceException e) {
        page = ConfigurationManager.getProperty("error");
        LOGGER.error("FixReturnCommand hasn't completed");
      }
      session.setAttribute(PAGE, page);
    }
  }
}
