package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class FindCodeCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    String code = request.getParameter("code");
    WelcomeService welcomeService = WelcomeService.getInstance();
    HttpSession session = request.getSession();
    String page = (String) session.getAttribute("page");
    int editionId = 0;
    try {
      editionId = welcomeService.editionIdByCode(code);
    } catch (ServiceException e) {
      page = ConfigurationManager.getProperty("error");
      LOGGER.error("FindCodeCommand hasn't completed");
    }
    session.setAttribute("code", code);
    if (editionId == 0) {
      session.setAttribute("addition", "block/edition_addition.jsp");
    } else {
      List<Book> books = new ArrayList<>();
      try {
        books = welcomeService.findBooksByEditionCode(code);
      } catch (ServiceException e) {
        page = ConfigurationManager.getProperty("error");
        LOGGER.error("FindCodeCommand hasn't completed");
      }
      session.setAttribute("addition", "block/book_addition.jsp");
      session.setAttribute("decommission", books);
    }
    session.setAttribute(PAGE, page);
  }
}
