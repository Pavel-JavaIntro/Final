package by.pavka.library.controller.command;

import by.pavka.library.controller.command.impl.*;

public enum CommandEnum {
  EMPTY(new EmptyCommand()),
  WELCOME(new WelcomeCommand()),
  SEARCH(new SearchCommand()),
  LOGIN(new LoginCommand()),
  LOGOUT(new LogoutCommand()),
  FIND_EDITION(new FindEditionCommand()),
  SET_LANG(new SetLanguageCommand()),
  SELECT_BOOK(new SelectBookCommand()),
  BASKET(new BasketCommand()),
  ORDER_BOOK(new OrderBookCommand()),
  SHOW_ORDERS(new ShowOrdersCommand()),
  PREPARE_BOOK(new PrepareBookCommand()),
  SHOW_BOOKS(new ShowBooksCommand()),
  DISPATCH(new DispatchCommand()),
  BOOK_OPTIONS(new BookOptionsCommand()),
  FIND_CODE(new FindCodeCommand()),
  ADD_BOOK(new AddBookCommand()),
  ADD_EDITION(new AddEditionCommand()),
  DECOMMISSION_BOOK(new DecommissionBookCommand()),
  USER_OPTIONS(new UserOptionsCommand()),
  FIND_USER(new FindUsersCommand()),
  ADD_USER(new AddUserCommand());

  private final ActionCommand command;

  CommandEnum(ActionCommand command) {
    this.command = command;
  }

  public ActionCommand getCommand() {
    return command;
  }
}
