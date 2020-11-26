package by.pavka.library.newversion.command;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.LibraryService;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeStatusCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    PageRouter pageRouter = new PageRouter();
    String user = request.getParameter(USER);
    String role = request.getParameter(STATUS);
    if (user != null && role != null) {
      int userId = Integer.parseInt(user);
      int roleId = ConstantManager.getRoleId(role);
      LibraryService service = LibraryService.getInstance();
      try {
        service.changeStatus(userId, roleId);
      } catch (ServiceException e) {
        pageRouter.setPage(PageRouter.ERROR);
        LOGGER.error("ChangeStatusCommand hasn't completed");
      }
    }
    return pageRouter;
  }
}
