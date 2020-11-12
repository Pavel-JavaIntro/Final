package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ReturnCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute(PAGE);
    int bookId = Integer.parseInt(request.getParameter("return"));
    WelcomeService welcomeService = WelcomeService.getInstance();
    Book book = null;
    try {
      book = welcomeService.findBookById(bookId);
    } catch (ServiceException e) {
      page = ConfigurationManager.getProperty("error");
      LOGGER.error("ReturnCommand hasn't completed");
    }
    session.setAttribute(PAGE, page);
    session.setAttribute("returning", book);
  }
}
