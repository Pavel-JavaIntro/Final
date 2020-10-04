package by.pavka.library.controller.command.impl;

import by.pavka.library.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements ActionCommand {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) {
    //Empty method for EmptyCommand
  }
}
