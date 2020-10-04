package by.pavka.library.controller;

import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.controller.command.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LibraryServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    process(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    process(request, response);
  }

  private void process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
//    String surname = request.getParameter("surname");
////    String name = request.getParameter("name");
////    String patronymicS = request.getParameter("patronymic");
////    String yearS = request.getParameter("year");
////    int patronymic = Integer.parseInt(patronymicS);
////    int year = Integer.parseInt(yearS);
//
//    request.setAttribute("surname", surname);
//    response.setContentType("text/html; charset=UTF-8");
//    try {
//      LibraryDao<Genre> genre = new SimpleLibraryDao(TableEntityMapper.GENRE);
////      int n = Integer.parseInt(name);
////      EntityField<String> field = new EntityField<>("description");
////      field.setValue(surname);
////      genre.update(n, field);
////
//      Genre g = new Genre();
//      g.setValue("description", surname);
////      g.setValue("title", name);
////      g.setValue("genreId", patronymic);
////      g.setValue("year", year);
////      g.setValue("deliveries", 0);
////      genre.add(g);
//      List<Genre> genres = genre.read();
//      genre.close();
//      request.setAttribute("genres", genres);
//    } catch (DaoException e) {
//      //TODO
//      e.printStackTrace();
//    }
    //TODO this is only for test purposes and should be re-written
    ActionFactory client = new ActionFactory();
    ActionCommand command = client.defineCommand(request);
    command.execute(request, response);
    String page = (String)request.getAttribute("page");
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
    requestDispatcher.forward(request, response);
  }
}
