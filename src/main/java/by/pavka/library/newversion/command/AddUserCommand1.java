package by.pavka.library.newversion.command;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.ConfirmationMailSender;
import by.pavka.library.entity.impl.User;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.LibraryService;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddUserCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    PageRouter pageRouter = new PageRouter();
    HttpSession session = request.getSession();
    String surname = (String)session.getAttribute(USER_SURNAME);
    String name = (String)session.getAttribute(USER_NAME);
    if (surname == null || name == null) {
      return pageRouter;
    }
    String address = request.getParameter(USER_ADDRESS);
    String phone = request.getParameter(USER_PHONE);
    String email = request.getParameter(USER_EMAIL);
    String role = request.getParameter(USER_ROLE);
    int roleId = ConstantManager.getRoleId(role);
    User user = new User();
    user.setValue(User.ROLE_ID, roleId);
    user.setValue(User.SURNAME, surname);
    user.setValue(User.NAME, name);
    user.setValue(User.ADDRESS, address);
    if (!phone.isEmpty()) {
      user.setValue(User.PHONE, phone);
    }
    if (!email.isEmpty()) {
      user.setValue(User.EMAIL, email);
      int password = ConfirmationMailSender.sendInvitation(email).hashCode();
      user.setValue(User.PASSWORD, password);
    }
    LibraryService service = LibraryService.getInstance();
    try {
      service.addUser(user);
      session.setAttribute(RESULT, PageRouter.RESULT_SUCCESS);
      session.removeAttribute(USERS);
      session.removeAttribute(USER_NAME);
      session.removeAttribute(USER_SURNAME);
    } catch (ServiceException e) {
      pageRouter.setPage(PageRouter.ERROR);
      LOGGER.error("AddUserCommand hasn't completed");
    }
    return pageRouter;
  }
}
