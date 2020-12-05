package by.pavka.library.newversion.tag;

import by.pavka.library.BookOrder;
import by.pavka.library.MessageManager;
import by.pavka.library.entity.EditionInfo;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.newversion.Command1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Locale;

public class OrderTagHandler extends SimpleTagSupport {
  private static final Logger LOGGER = LogManager.getLogger(OrderTagHandler.class);
  private BookOrder order;
  private EditionInfo edition;
  private boolean standard;

  @Override
  public void doTag() throws JspException, IOException {
    int userId = order.getUserId();
    int bookId = edition.getBook().getId();
    String editionInfo = edition.toString();
    String location = ConstantManager.getLocationById(edition.getLocationId());
    String standardLocation = ConstantManager.getLocationById(edition.getStandardLocationId());
    String language = (String) getJspContext().findAttribute(Command1.SESSION_ATTRIBUTE_LANGUAGE);
    Locale locale = language == null ? Locale.getDefault() : new Locale(language);
    String process = MessageManager.getProperty("message.process", locale);
    String result = null;
    if (!standard) {
      result = String.format(process, userId, bookId, editionInfo, location);
    } else {
      result = String.format(process, userId, bookId, editionInfo, standardLocation);
      String transfer = MessageManager.getProperty("message.transfer", locale);
      result += String.format(transfer, location);
    }
    getJspContext().getOut().print(String.format(result));
  }

  public void setOrder(BookOrder order) {
    this.order = order;
  }

  public void setEdition(EditionInfo edition) {
    this.edition = edition;
  }

  public void setStandard(boolean standard) {
    this.standard = standard;
  }
}
