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
    String page = (String)session.getAttribute("page");
    String id = request.getParameter("edition");
    int intId = Integer.parseInt(id);
    AppClient client = (AppClient)session.getAttribute("client");
    System.out.println("ONE");
    Set<EditionInfo> all = (Set<EditionInfo>)session.getAttribute("editions");
    System.out.println("TWO");
    for (EditionInfo info : all) {
      if (intId == info.getEdition().getId()) {
        client.addEditionInfo(info);
      }
    }
//    WelcomeService welcomeService = WelcomeService.getInstance();
//    try {
//      Book book = welcomeService.findBookByEdition(intId);
//      if(!client.addBook(book)) {
//        request.setAttribute("addition", false);
//      }
//    } catch (ServiceException e) {
//      page = ConfigurationManager.getProperty("error");
//      logger.error("FindBookCommand hasn't completed");
//    }
  }
}
