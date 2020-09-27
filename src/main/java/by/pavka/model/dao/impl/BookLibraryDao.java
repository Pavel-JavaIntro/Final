package by.pavka.model.dao.impl;

import by.pavka.model.dao.DaoException;
import by.pavka.model.dao.SqlLibraryDao;
import by.pavka.entity.criteria.EntityField;
import by.pavka.entity.impl.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookLibraryDao extends SqlLibraryDao<Book> {

  public BookLibraryDao() throws DaoException {
  }

  @Override
  public String getTableName() {
    return "books";
  }

  @Override
  protected Book formEntity(ResultSet resultSet) throws SQLException {
    //TODO
    return null;
  }

  @Override
  protected String formAddRequest(Book entity) {
    //TODO
    return null;
  }


  public void add(Book entity) {
//    int editionId = entity.getEditionId();
//    boolean forDelivery = entity.isForDelivery();
//    int locationId = entity.getLocationId();
//    int readerId = entity.getReaderId();
//    Date dueDate = entity.getDueDate();
//    int standardLocationId = entity.getStandardLocationId();
//    PreparedStatement statement = null;
//    try {
//      statement = connector.obtainPreparedStatement(ADD_BOOK);
//      statement.setInt(1, editionId);
//      statement.executeUpdate();
//      return true;
//    } catch (DaoException | SQLException e) {
//      //TODO
//    } finally {
//      connector.closeStatement(statement);
//    }
  }

  @Override
  public void update(int id, EntityField... fields) {

  }

  @Override
  public Book createEntity() {
    return new Book();
  }
}
