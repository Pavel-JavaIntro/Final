package by.pavka.library.newversion.command;

import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.client.AppClient;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class SelectBookCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String id = request.getParameter(EDITION);
    int intId = Integer.parseInt(id);
    AppClient client = (AppClient) session.getAttribute(SESSION_ATTRIBUTE_CLIENT);
    Set<EditionInfo> editionInfos = (Set<EditionInfo>) session.getAttribute(EDITIONS);
    for (EditionInfo info : editionInfos) {
      if (intId == info.getEdition().getId()) {
        client.addEditionInfo(info);
      }
    }
    return new PageRouter();
  }
}
