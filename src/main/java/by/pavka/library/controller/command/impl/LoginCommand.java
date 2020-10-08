package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.MessageManager;
import by.pavka.library.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO just a kind of stub
public class LoginCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) {
    String surname = request.getParameter("surname");
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String page = null;
    if ("Касичев".equals(surname) && "Павел".equals(name) && password.hashCode() == 92668751) {
      page = ConfigurationManager.getProperty("path.page.main");
    } else {
      request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message" +
              ".loginerror"));
      page = ConfigurationManager.getProperty("path.page.error");
    }
    request.setAttribute("page", page);
  }
}
