package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) {
    //TODO
    WelcomeService welcomeService = WelcomeService.getInstance();
    try {
      int books = welcomeService.countBooks();
      int users = welcomeService.countUsers();
      ServletContext servletContext = request.getServletContext();
      servletContext.setAttribute("books", books);
      servletContext.setAttribute("users", users);
      request.setAttribute("page", ConfigurationManager.getProperty("login"));
    } catch (ServiceException e) {
      request.setAttribute("page", ConfigurationManager.getProperty("error"));
    }
  }
}
