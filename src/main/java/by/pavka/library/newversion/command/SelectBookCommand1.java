package by.pavka.library.newversion.command;

import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.client.AppClient;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.newversion.Command1;
import by.pavka.library.newversion.PageRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class SelectBookCommand1 implements Command1 {
  @Override
  public PageRouter execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String idS = request.getParameter(EDITION);
    String desk = request.getParameter(DESK);
    System.out.println("DESK = " + desk);
    int id = Integer.parseInt(idS);
    AppClient client = (AppClient) session.getAttribute(SESSION_ATTRIBUTE_CLIENT);
    Set<EditionInfo> editionInfos = (Set<EditionInfo>) session.getAttribute(EDITIONS);
    for (EditionInfo info : editionInfos) {
      if (id == info.getEdition().getId()) {
        client.addEditionInfo(info);
        if (desk != null) {
          info.setLocationId(ConstantManager.LOCATION_DELIVERY_DESK_RESERVE);
        } else {
          info.setLocationId(ConstantManager.LOCATION_READING_HALL_RESERVE);
        }
      }
    }
    return new PageRouter();
  }
}
