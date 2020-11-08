package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.ConfirmationMailSender;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.impl.User;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddUserCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute(PAGE);
    String surname = (String)session.getAttribute("surname");
    String name = (String)session.getAttribute("name");
    String address = request.getParameter("address");
    String phone = request.getParameter("phone");
    String email = request.getParameter("email");
    String role = request.getParameter("role");
    int roleId = ConstantManager.getRoleId(role);
    User user = new User();
    user.setValue("roleId", roleId);
    user.setValue("surname", surname);
    user.setValue("name", name);
    user.setValue("address", address);
    if (!phone.isEmpty()) {
      user.setValue("phone", phone);
    }
    if (!email.isEmpty()) {
      user.setValue("email", email);
      int password = ConfirmationMailSender.sendInvitation(email).hashCode();
      user.setValue("password", password);
    }
    WelcomeService welcomeService = WelcomeService.getInstance();
    try {
      welcomeService.addUser(user);
    } catch (ServiceException e) {
      page = ConfigurationManager.getProperty("error");
      LOGGER.error("AddUserCommand hasn't completed");
    }
    session.setAttribute(PAGE, page);
  }
}
