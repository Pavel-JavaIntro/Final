package by.pavka.library.newversion.tag;

import by.pavka.library.MessageManager;
import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.impl.Edition;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.newversion.Command1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Locale;

public class EditionTagHandler extends SimpleTagSupport {
  private static final Logger LOGGER = LogManager.getLogger(EditionTagHandler.class);
  private EditionInfo edition;

  @Override
  public void doTag() throws JspException, IOException {
    String language = (String) getJspContext().findAttribute(Command1.SESSION_ATTRIBUTE_LANGUAGE);
    Locale locale = language == null ? Locale.getDefault() : new Locale(language);
    String info = MessageManager.getProperty("message.ordereditioninfo", locale);
    Edition ed = edition.getEdition();
    try {
      String title = (String)ed.fieldForName(Edition.TITLE).getValue();
      String authors = edition.getAuthors();
      String destination = ConstantManager.getLocationById(edition.getLocationId());
      getJspContext().getOut().print(String.format(info, title, authors, destination));
    } catch (LibraryEntityException e) {
      LOGGER.error("Cannot read edition-info tag");
      throw new SkipPageException("Cannot read edition-info tag");
    }
  }

  public void setEdition(EditionInfo editionInfo) {
    this.edition = editionInfo;
  }
}
