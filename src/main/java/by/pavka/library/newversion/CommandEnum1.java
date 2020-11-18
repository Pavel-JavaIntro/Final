package by.pavka.library.newversion;

import by.pavka.library.controller.command.ActionCommand;
import by.pavka.library.newversion.command.*;


public enum CommandEnum1 {
  EMPTY(new EmptyCommand1()),
  WELCOME(new WelcomeCommand1()),
  SEARCH(new SearchCommand1()),
  LOGIN(new LoginCommand1()),
  LOGOUT(new LogoutCommand1()),
  FIND_EDITION(new FindEditionCommand1()),
  SET_LANG(new SetLanguageCommand1()),
  SELECT_BOOK(new SelectBookCommand1()),
  BASKET(new BasketCommand1()),
  ORDER_BOOK(new OrderBookCommand1()),
  SHOW_ORDERS(new ShowOrdersCommand1()),
  PREPARE_BOOK(new PrepareBookCommand1()),
  SHOW_BOOKS(new ShowBooksCommand1()),
  DISPATCH(new DispatchCommand1()),
  BOOK_OPTIONS(new BookOptionsCommand1()),
  FIND_CODE(new FindCodeCommand1()),
  ADD_BOOK(new AddBookCommand1()),
  ADD_EDITION(new AddEditionCommand1()),
  DECOMMISSION_BOOK(new DecommissionBookCommand1()),
  USER_OPTIONS(new UserOptionsCommand1());
//  FIND_USER(new FindUsersCommand1()),
//  ADD_USER(new AddUserCommand1()),
//  CHANGE_STATUS(new ChangeStatusCommand1()),
//  RETURN_BOOKS(new ReturnBookCommand1()),
//  RETURN(new ReturnCommand1()),
//  FIX_RETURN(new FixReturnCommand1());

  private final Command1 command;

  CommandEnum1(Command1 command) {
    this.command = command;
  }

  public Command1 getCommand() {
    return command;
  }
}
