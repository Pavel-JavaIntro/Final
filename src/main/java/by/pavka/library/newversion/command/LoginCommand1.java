package by.pavka.library.newversion.command;

import by.pavka.library.MessageManager;
import by.pavka.library.controller.LibValidator;
import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.client.AppClient;
import by.pavka.library.entity.impl.User;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.LibraryService;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LoginCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    PageRouter pageRouter = new PageRouter();
    String surname = request.getParameter(SURNAME);
    String name = request.getParameter(NAME);
    String password = request.getParameter(PASSWORD);
    HttpSession session = request.getSession();
    String language = (String)session.getAttribute(SESSION_ATTRIBUTE_LANGUAGE);
    Locale locale = language == null ? Locale.getDefault() : new Locale(language);
    if (LibValidator.validateLogin(surname, name, password)) {
      LibraryService service = LibraryService.getInstance();
      try {
        User user = service.auth(surname, name, password);
        if (user != null) {
          AppClient client =
              new AppClient() {
                @Override
                public String getRole() {
                  try {
                    int roleId = user.getRoleId();
                    return ConstantManager.getRoleById(roleId);
                  } catch (LibraryEntityException e) {
                    LOGGER.warn("Login failed");
                    return ConstantManager.GUEST;
                  }
                }
              };
          try {
            client.setId(user.getId());
            client.setSurname(user.getSurname());
            client.setName(user.getName());
            client.setEmail(user.getEmail());
            session.setAttribute(SESSION_ATTRIBUTE_CLIENT, client);
          } catch (LibraryEntityException e) {
            pageRouter.setPage(PageRouter.ERROR);
            LOGGER.warn("Login failed");
          }
        } else {
          System.out.println(locale);
          request.setAttribute(ERROR_LOGIN_PASS, MessageManager.getProperty(
              "message.loginerror", locale));
        }
      } catch (ServiceException e) {
        pageRouter.setPage(PageRouter.ERROR);
        LOGGER.error("LoginCommand hasn't completed");
      }
    } else {
      request.setAttribute(ERROR_LOGIN_PASS, MessageManager.getProperty(
          "message.emptyfields", locale));
    }
    return pageRouter;
  }
}
