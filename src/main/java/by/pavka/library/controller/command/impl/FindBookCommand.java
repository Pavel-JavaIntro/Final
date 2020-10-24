package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindBookCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    String title = request.getParameter("title");
    String author = request.getParameter("author");
    WelcomeService welcomeService = WelcomeService.getInstance();
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute(PAGE);
    List<Book> books = null;
    try {
      books = welcomeService.findBooks(title, author);
      session.setAttribute("books", books);
    } catch (ServiceException e) {
      page = ConfigurationManager.getProperty("error");
      LOGGER.error("FindBookCommand hasn't completed");
    }
    session.setAttribute(PAGE, page);
  }
}
