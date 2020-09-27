package by.pavka.controller;

import by.pavka.model.dao.DaoException;
import by.pavka.model.dao.LibraryDao;
import by.pavka.model.dao.impl.GenreLibraryDao;
import by.pavka.entity.criteria.EntityField;
import by.pavka.entity.impl.Genre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    String surname = request.getParameter("surname");
    String name = request.getParameter("name");
    request.setAttribute("surname", surname);
    try {
      LibraryDao<Genre> genre = new GenreLibraryDao();
      int n = Integer.parseInt(name);
      EntityField<String> field = new EntityField<>("description");
      field.setValue(surname);
      genre.update(n, field);

      Genre g = new Genre();
      g.setValue("description", surname);
      //genre.add(g);
      List<Genre> genres = genre.read();
      genre.close();
      request.setAttribute("genres", genres);
    } catch (DaoException e) {
      //TODO
      e.printStackTrace();
    }
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/success.jsp");
    requestDispatcher.forward(request, response);
  }
}
