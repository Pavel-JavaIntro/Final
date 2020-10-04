package by.pavka.library.controller;

import by.pavka.library.entity.impl.Author;
import by.pavka.library.entity.impl.Edition;
import by.pavka.library.entity.impl.Genre;
import by.pavka.library.model.dao.DaoException;
import by.pavka.library.model.dao.LibraryDao;
import by.pavka.library.model.dao.impl.SimpleLibraryDao;
import by.pavka.library.model.mapper.TableEntityMapper;

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
    String patronymicS = request.getParameter("patronymic");
    String yearS = request.getParameter("year");
    int patronymic = Integer.parseInt(patronymicS);
    int year = Integer.parseInt(yearS);

    request.setAttribute("surname", surname);
    response.setContentType("text/html; charset=UTF-8");
    try {
      LibraryDao<Edition> genre = new SimpleLibraryDao(TableEntityMapper.EDITION);
//      int n = Integer.parseInt(name);
//      EntityField<String> field = new EntityField<>("description");
//      field.setValue(surname);
//      genre.update(n, field);
//
      Edition g = new Edition();
      g.setValue("standardNumber", surname);
      g.setValue("title", name);
      g.setValue("genreId", patronymic);
      g.setValue("year", year);
      g.setValue("deliveries", 0);
      genre.add(g);
      List<Edition> genres = genre.read();
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
