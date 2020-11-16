package by.pavka.library.newversion.command;

import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetLanguageCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    String language = request.getParameter(LANGUAGE);
    HttpSession session = request.getSession();
    session.setAttribute(SESSION_ATTRIBUTE_LANGUAGE, language);
    return new PageRouter();
  }
}
