package by.pavka.library.controller.command.impl;

import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) {
    //TODO
    WelcomeService welcomeService = WelcomeService.getInstance();
    try {
      int count = welcomeService.countBooks();
      request.setAttribute("count", count);
      request.setAttribute("page", "/jsp/welcome.jsp");
    } catch (ServiceException e) {
      //TODO will be implemented by re-sending to error page
    }
  }
}
