package by.pavka.library.newversion.command;

import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.impl.Edition;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.LibraryService;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindEditionCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    String title = request.getParameter(TITLE);
    String author = request.getParameter(AUTHOR);
    PageRouter pageRouter = new PageRouter();
    LibraryService service = LibraryService.getInstance();
    HttpSession session = request.getSession();
    List<Edition> editions;
    try {
      editions = service.findEditions(title, author);
    } catch (ServiceException e) {
      pageRouter.setPage(PageRouter.ERROR);
      LOGGER.error("FindEditionCommand hasn't completed");
      return pageRouter;
    }
    Set<EditionInfo> infos = new HashSet<>();
    try {
      for (Edition e : editions) {
        EditionInfo info = new EditionInfo();
        info.setEdition(e);
        service.bindAuthors(info);
        service.bindBookAndLocation(info);
        infos.add(info);
      }
      session.setAttribute(EDITIONS, infos);
    } catch (ServiceException e) {
      pageRouter.setPage(PageRouter.ERROR);
      LOGGER.error("FindEditionCommand hasn't completed");
    }
    return pageRouter;
  }
}
