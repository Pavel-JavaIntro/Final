package by.pavka.library.newversion;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LibraryTagHandler extends SimpleTagSupport {
  private String user;

  @Override
  public void doTag() throws JspException, IOException {
    getJspContext().getOut().write("Hello, " + user + "! You are using a tag.");
  }

  public void setUser(String user) {
    this.user = user;
  }
}
