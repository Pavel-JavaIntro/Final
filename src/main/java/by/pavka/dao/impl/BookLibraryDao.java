package by.pavka.dao.impl;

import by.pavka.ConnectionWrapper;
import by.pavka.DaoException;
import by.pavka.dao.LibraryDao;
import by.pavka.entity.impl.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class BookLibraryDao extends LibraryDao<Book> {
  private static final String ADD_BOOK =
      "INSERT INTO books (edition_id, for_delivery, location_id, reader_id, due_date, standard_location_id) "
          + " VALUES(?, ?, ?, ?, ?, ?)";

  private ConnectionWrapper connector = new ConnectionWrapper();

  public boolean add(Book entity) {
    int editionId = entity.getEditionId();
    boolean forDelivery = entity.isForDelivery();
    int locationId = entity.getLocationId();
    int readerId = entity.getReaderId();
    Date dueDate = entity.getDueDate();
    int standardLocationId = entity.getStandardLocationId();
    PreparedStatement statement = null;
    try {
      statement = connector.obtainPreparedStatement(ADD_BOOK);
      statement.setInt(1, editionId);
      statement.executeUpdate();
      return true;
    } catch (DaoException | SQLException e) {
      //TODO
    } finally {
      connector.closeStatement(statement);
    }
    return false;
  }
}
