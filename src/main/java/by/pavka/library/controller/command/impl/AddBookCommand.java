package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddBookCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String) session.getAttribute(PAGE);
    String code = (String) session.getAttribute("code");
    String loc = request.getParameter("booklocation");
    int locationId = loc.isEmpty() ? 0 : Integer.parseInt(loc);
    List<Book> books = (List<Book>) session.getAttribute("decommission");
    int editionId = 0;
    if (books != null && !books.isEmpty()) {
      Book book = books.get(0);
      try {
        editionId = (int) book.fieldForName("editionId").getValue();
        if (locationId == 0) {
          locationId = (int) book.fieldForName("standardLocationId").getValue();
        }
      } catch (LibraryEntityException e) {
        page = ConfigurationManager.getProperty("error");
        LOGGER.error("AddBookCommand hasn't completed");
      }
    }
    Book newBook = new Book();
    WelcomeService welcomeService = WelcomeService.getInstance();
    try {
      if (editionId == 0) {
        System.out.println(code);
        editionId = welcomeService.editionIdByCode(code);
        System.out.println("EDITION ID = "+ editionId);
      }
      if (locationId != 0) {
        newBook.fieldForName("locationId").setValue(locationId);
        newBook.fieldForName("standardLocationId").setValue(locationId);
      }
      newBook.fieldForName("editionId").setValue(editionId);
      System.out.println("OK?");
      welcomeService.addBook(newBook);
    } catch (LibraryEntityException | ServiceException e) {
      page = ConfigurationManager.getProperty("error");
      LOGGER.error("AddBookCommand hasn't completed");
    }
    session.setAttribute(PAGE, page);
  }
}
