package by.pavka.library.newversion;

import by.pavka.library.MessageManager;
import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.impl.User;
import by.pavka.library.model.mapper.ConstantManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Locale;

public class UserTagHandler extends SimpleTagSupport {
  private User user;

  @Override
  public void doTag() throws JspException, IOException {
    try {
      String name = user.getName();
      String surname = user.getSurname();
      String email = user.getEmail();
      int roleId = user.getRoleId();
      String role = ConstantManager.getRoleById(roleId);
      String language = (String)getJspContext().findAttribute(Command1.SESSION_ATTRIBUTE_LANGUAGE);
      Locale locale = language == null ? Locale.getDefault() : new Locale(language);
      String userInfo = MessageManager.getProperty("message.userinfo", locale);
      getJspContext().getOut().print(String.format(userInfo, name, surname, email, role));
    } catch (LibraryEntityException e) {
      throw new SkipPageException();
    }
  }

  public void setUser(User user) {
    this.user = user;
  }
}
