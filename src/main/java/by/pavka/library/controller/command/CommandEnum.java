package by.pavka.library.controller.command;

import by.pavka.library.controller.command.impl.EmptyCommand;
import by.pavka.library.controller.command.impl.SearchCommand;
import by.pavka.library.controller.command.impl.WelcomeCommand;

public enum CommandEnum {
  EMPTY(new EmptyCommand()),
  WELCOME(new WelcomeCommand()),
  SEARCH(new SearchCommand());

  private ActionCommand command;

  CommandEnum(ActionCommand command) {
    this.command = command;
  }

  public ActionCommand getCommand() {
    return command;
  }
}
