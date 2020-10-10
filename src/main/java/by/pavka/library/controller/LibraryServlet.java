package by.pavka.library.controller;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.MessageManager;
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
    // TODO this is only for test purposes and should be re-written
    ActionFactory client = new ActionFactory();
    ActionCommand command = client.defineCommand(request);
    command.execute(request, response);
    String page = (String) request.getAttribute("page");
    if (page != null) {
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
      requestDispatcher.forward(request, response);
    } else {
      page = ConfigurationManager.getProperty("index");
      request.getSession().setAttribute("nullPage", MessageManager.getProperty("nullpage"));
      response.sendRedirect(request.getContextPath() + page);
    }
  }
}
