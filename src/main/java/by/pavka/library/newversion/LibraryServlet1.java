package by.pavka.library.newversion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LibraryServlet1 extends HttpServlet {
  private static final Logger LOGGER = LogManager.getLogger(LibraryServlet1.class);
  private static final String WELCOME = "welcome";

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    process(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (request.getParameter(Command1.COMMAND).equals(WELCOME)) {
      process(request, response);
    } else {
      response.sendError(403);
    }
  }

  private void process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Command1 command = ActionFactory1.defineCommand(request);
    LOGGER.info("Execution: " + command.getClass().getSimpleName());
    PageRouter pageRouter = command.executeCommand(request);
    String page = pageRouter.getPage();
    if (pageRouter.getType() == PageRouter.TransitionType.FORWARD) {
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
      requestDispatcher.forward(request, response);
    } else {
      response.sendRedirect(page);
    }
  }
}
