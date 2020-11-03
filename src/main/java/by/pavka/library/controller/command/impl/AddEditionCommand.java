package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.criteria.Criteria;
import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.entity.impl.Author;
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
      authors[i][0] = surname.isEmpty() ? null : request.getParameter(surname);
      authors[i][1] = name.isEmpty() ? null : request.getParameter(name);
      authors[i][2] = secondname.isEmpty() ? null : request.getParameter(secondname);
    }
    Edition edition = new Edition();
    Book book = new Book();
    WelcomeService welcomeService = WelcomeService.getInstance();
    edition.setValue("standardNumber", code);
    edition.setValue("genreId", genre);
    edition.setValue("title", title);
    if (year != 0) {
      edition.setValue("year", year);
    }
    if (locationId != 0) {
      book.setValue("locationId", locationId);
      book.setValue("standardLocationId", locationId);
    }
    int[] ids = new int[3];
    Author[] athrs = new Author[3];
    Criteria[] criteria = new Criteria[3];
    for (int i = 1; i < 3; i++) {
      if (authors[i][0] != null) {
        Author author = new Author();
        author.setValue("surname", authors[i][0]);
        EntityField<String> surname = new EntityField<>("surname");
        surname.setValue(authors[i][0]);
        criteria[i] = new Criteria();
        criteria[i].addConstraint(surname);
        if (authors[i][1] != null) {
          author.setValue("firstName", authors[i][1]);
          EntityField<String> name = new EntityField<>("firstName");
          name.setValue(authors[i][1]);
          criteria[i].addConstraint(name);
        }
        if (authors[1][2] != null) {
          author.setValue("secondName", authors[i][2]);
          EntityField<String> secondName = new EntityField<>("secondName");
          secondName.setValue(authors[i][2]);
          criteria[i].addConstraint(secondName);
        }
        athrs[i] = author;
      }
    }

    try {
      int id = welcomeService.addEdition(edition);
      System.out.println("EDITION ID = " + id);
      book.setValue("editionId", id);
      welcomeService.addBook(book);
      for (int i = 0; i < 3; i++) {
        List<Author> authorList = welcomeService.findAuthors(criteria[i]);
        if (!authorList.isEmpty()) {
          ids[i] = authorList.get(0).getId();
        } else {
          ids[i] = welcomeService.addAuthor(athrs[i]);
        }
      }
      welcomeService.bindEditionAndAuthors(id, ids);

    } catch (ServiceException e) {
      page = ConfigurationManager.getProperty("error");
      LOGGER.error("AddEditionCommand hasn't completed");
    }
    session.setAttribute(PAGE, page);
  }
}
