package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.impl.User;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindUsersCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute(PAGE);
    String surname = request.getParameter("surname");
    String name = request.getParameter("name");
    WelcomeService welcomeService = WelcomeService.getInstance();
    List<User> users = null;
    try {
      users = welcomeService.findUsers(surname, name);
    } catch (ServiceException e) {
      page = ConfigurationManager.getProperty("error");
      LOGGER.error("FindUsersCommand hasn't completed");
    }
    session.setAttribute("surname", surname);
    session.setAttribute("name", name);
    if (!users.isEmpty()) {
      session.setAttribute("users", users);
    }
    session.setAttribute("user_addition", "block/user_addition.jsp");
    session.setAttribute(PAGE, page);
  }
}
