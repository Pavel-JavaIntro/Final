package by.pavka.library.controller.command.impl;

import by.pavka.library.ConfigurationManager;
import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.client.AppClient;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class SelectBookCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String page = (String)session.getAttribute(PAGE);
    String id = request.getParameter("edition");
    int intId = Integer.parseInt(id);
    AppClient client = (AppClient)session.getAttribute("client");
    Set<EditionInfo> all = (Set<EditionInfo>)session.getAttribute("editions");
    for (EditionInfo info : all) {
      if (intId == info.getEdition().getId()) {
        client.addEditionInfo(info);
      }
    }
    session.setAttribute(PAGE, page);
  }
}
