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
  private static final String INFO = "\"%s\", %s, %s";
  private EditionInfo edition;
  private boolean availability;

  @Override
  public void doTag() throws JspException, IOException {
    String language = (String) getJspContext().findAttribute(Command1.SESSION_ATTRIBUTE_LANGUAGE);
    Locale locale = language == null ? Locale.getDefault() : new Locale(language);
    Edition ed = edition.getEdition();
    String info = null;
    String extra = null;
    try {
      String title = (String)ed.fieldForName(Edition.TITLE).getValue();
      String authors = edition.getAuthors();
      if (!availability) {
        info = MessageManager.getProperty("message.ordereditioninfo", locale);
        extra = ConstantManager.getLocationById(edition.getLocationId());
      } else {
        info = INFO;
        extra = edition.getAvailability();
      }
      getJspContext().getOut().print(String.format(info, title, authors, extra));
    } catch (LibraryEntityException e) {
      LOGGER.error("Cannot read edition-info tag");
      throw new SkipPageException("Cannot read edition-info tag");
    }
  }

  public void setEdition(EditionInfo editionInfo) {
    this.edition = editionInfo;
  }

  public void setAvailability(boolean availability) {
    this.availability = availability;
  }
}
