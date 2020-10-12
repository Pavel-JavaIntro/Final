package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.MessageManager;
import by.pavka.library.controller.LibValidator;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.client.AppClient;
import by.pavka.library.entity.impl.Role;
import by.pavka.library.entity.impl.User;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) {
    String surname = request.getParameter("surname");
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute("page");
    if (LibValidator.validateLogin(surname, name, password)) {
      WelcomeService welcomeService = WelcomeService.getInstance();
      try {
        User user = welcomeService.auth(surname, name, password);
        if (user != null) {
          AppClient client =
              new AppClient() {
                @Override
                public String getRole() {
                  try {
                    int roleId = user.getRoleId();
                    return ConstantManager.getRoleById(roleId);
                  } catch (LibraryEntityException e) {
                    return ConstantManager.VISITOR;
                  }
                }
              };
          try {
            client.setSurname(user.getSurname());
            client.setName(user.getName());
            client.setEmail(user.getEmail());
            session.setAttribute("client", client);
          } catch (LibraryEntityException e) {
            page = ConfigurationManager.getProperty("error");
          }
        } else {
          request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("loginerror"));
        }
      } catch (ServiceException e) {
        page = ConfigurationManager.getProperty("error");
        logger.error("LoginCommand hasn't completed");
      }
    } else {
      request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("emptyfields"));
    }
    session.setAttribute("page", page);
  }
}
