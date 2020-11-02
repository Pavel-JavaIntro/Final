package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.entity.impl.Edition;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddEditionCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String) session.getAttribute(PAGE);
    String code = (String) session.getAttribute("code");
    int genre = Integer.parseInt(request.getParameter("genre"));
    String yearS = request.getParameter("bookyear");
    int year = yearS.isEmpty()? 0 : Integer.parseInt(yearS);
    String title = request.getParameter("booktitle");
    String locationS = request.getParameter("booklocation");
    int locationId = yearS.isEmpty()? 0 : Integer.parseInt(locationS);
    String[][] authors = new String[3][3];
    for (int i = 0; i < 3; i++) {
      String surname = "surname" + i;
      String name = "name" + i;
      String secondname = "secondname" + i;
      authors[i][1] = surname.isEmpty() ? null : surname;
      authors[i][2] = name.isEmpty() ? null : name;
      authors[i][3] = secondname.isEmpty() ? null : secondname;
    }
    Edition edition = new Edition();
    Book book = new Book();
    WelcomeService welcomeService = WelcomeService.getInstance();
    edition.setValue("standard_number", code);
    edition.setValue("genre", genre);
    edition.setValue("title", title);
    if (year != 0) {
      edition.setValue("year", year);
    }
    if (locationId != 0) {
      book.setValue("locationId", locationId);
      book.setValue("standardLocationId", locationId);
    }

    try {
      int id = welcomeService.addEdition(edition);
      book.setValue("editionId", id);
      welcomeService.addBook(book);
    } catch (ServiceException e) {
      page = ConfigurationManager.getProperty("error");
      LOGGER.error("AddEditionCommand hasn't completed");
    }
    session.setAttribute(PAGE, page);
  }
}
