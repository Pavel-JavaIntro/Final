package by.pavka.library.controller;

import by.pavka.library.model.TableEntityMapper;
import by.pavka.library.model.dao.DaoException;
import by.pavka.library.model.dao.LibraryDao;
import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.entity.impl.Genre;
import by.pavka.library.model.dao.impl.SimpleLibraryDao;

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
    response.setContentType("text/html; charset=UTF-8");
    try {
      LibraryDao<Genre> genre = new SimpleLibraryDao(TableEntityMapper.getEntry(1));
      int n = Integer.parseInt(name);
      EntityField<String> field = new EntityField<>("description");
      field.setValue(surname);
      genre.update(n, field);
//
//      Genre g = new Genre();
//      g.setValue("description", surname);
      //genre.add(g);
      List<Genre> genres = genre.read();
      genre.close();
      request.setAttribute("genres", genres);
    } catch (DaoException e) {
      //TODO
      e.printStackTrace();
    }
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/success.jsp");
    requestDispatcher.forward(request, response);
  }
}
