package by.pavka.library.controller.command.impl;

import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.entity.client.AppClient;
import by.pavka.library.model.mapper.ConstantManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    AppClient client = new AppClient() {
      @Override
      public String getRole() {
        return ConstantManager.VISITOR;
      }
    };
    session.setAttribute("client", client);
  }
}
