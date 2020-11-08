package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeStatusCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute(PAGE);
    int userId = Integer.parseInt(request.getParameter("user"));
    String role = request.getParameter("status");
    int roleId = ConstantManager.getRoleId(role);
    WelcomeService welcomeService = WelcomeService.getInstance();
    try {
      welcomeService.changeStatus(userId, roleId);
    } catch (ServiceException e) {
      page = ConfigurationManager.getProperty("error");
      LOGGER.error("ChangeStatusCommand hasn't completed");
    }
    session.setAttribute(PAGE, page);
  }
}
