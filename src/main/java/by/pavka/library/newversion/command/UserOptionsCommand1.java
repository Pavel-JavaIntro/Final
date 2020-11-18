package by.pavka.library.newversion.command;

import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;

public class UserOptionsCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    return new PageRouter(PageRouter.PROCESS_USERS);
  }
}
