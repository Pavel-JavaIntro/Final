package by.pavka.library.newversion.command;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.entity.client.AppClient;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.LibraryService;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WelcomeCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    LibraryService service = LibraryService.getInstance();
    HttpSession session = request.getSession();
    PageRouter pageRouter = new PageRouter();
    try {
      int books = service.countBooks();
      int users = service.countUsers();
      ServletContext servletContext = request.getServletContext();
      servletContext.setAttribute(APP_ATTRIBUTE_BOOKS, books);
      servletContext.setAttribute(APP_ATTRIBUTE_USERS, users);
      if (session.isNew()) {
        AppClient client = new AppClient() {
          @Override
          public String getRole() {
            return ConstantManager.GUEST;
          }
        };
        session.setAttribute(SESSION_ATTRIBUTE_CLIENT, client);
      }
      pageRouter.setPage(PageRouter.WELCOME);
    } catch (ServiceException e) {
      pageRouter.setPage(PageRouter.ERROR);
      LOGGER.error("WelcomeCommand hasn't completed");
    }
    return pageRouter;
  }
}
