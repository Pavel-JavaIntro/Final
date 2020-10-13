package by.pavka.library.controller.command.impl;

import by.pavka.library.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO just a kind of stub
public class SearchCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) {
    request.getSession().setAttribute("page", "/jsp/search.jsp");
    logger.info("Searching...");
  }
}
