package by.pavka.library.controller.command;

import by.pavka.library.controller.command.impl.*;

public enum CommandEnum {
  EMPTY(new EmptyCommand()),
  WELCOME(new WelcomeCommand()),
  SEARCH(new SearchCommand()),
  LOGIN(new LoginCommand()),
  LOGOUT(new LogoutCommand()),
  FIND_BOOK(new FindBookCommand()),
  FIND_EDITION(new FindEditionCommand());

  private ActionCommand command;

  CommandEnum(ActionCommand command) {
    this.command = command;
  }

  public ActionCommand getCommand() {
    return command;
  }
}
