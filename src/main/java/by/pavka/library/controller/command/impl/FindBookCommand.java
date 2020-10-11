package by.pavka.library.controller.command.impl;

import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindBookCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) {
    String title = request.getParameter("title");
    String author1 = request.getParameter("author1");
    String author2 = request.getParameter("author2");
    int year = Integer.parseInt(request.getParameter("year"));

    WelcomeService welcomeService = WelcomeService.getInstance();
    HttpSession session = request.getSession();
    List<Book> books = null;
  }
}
