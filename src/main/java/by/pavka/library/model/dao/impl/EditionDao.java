package by.pavka.library.model.dao.impl;

import by.pavka.library.entity.impl.Author;
import by.pavka.library.entity.impl.Edition;
import by.pavka.library.model.ConnectionWrapper;
import by.pavka.library.model.dao.DaoException;
import by.pavka.library.model.dao.ManyToManyDao;
import by.pavka.library.model.mapper.TableEntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class EditionDao extends SimpleLibraryDao<Edition>
    implements ManyToManyDao<Edition, Author> {
  private static final String EDITION_ID = "edition_id";
  private static final String AUTHOR_ID = "author_id";
  private static final String SQL = "SELECT * FROM edition_author WHERE %s=?";
  private static final String BIND =
      "INSERT INTO edition_author (edition_id, author_id) VALUES (?, ?)";

  public EditionDao() throws DaoException {
    super(TableEntityMapper.EDITION);
  }

  @Override
  public Set<Integer> getFirst(int authorId) throws DaoException {
    return get(authorId, AUTHOR_ID, EDITION_ID);
//    Set<Integer> results = new HashSet<>();
//    String sql = String.format(SQL, AUTHOR_ID);
//    ConnectionWrapper connector = getConnector();
//    PreparedStatement statement = null;
//    ResultSet resultSet;
//    try {
//      statement = connector.obtainPreparedStatement(sql);
//      statement.setInt(1, authorId);
//      resultSet = statement.executeQuery();
//      while (resultSet.next()) {
//        results.add(resultSet.getInt(EDITION_ID));
//      }
//    } catch (DaoException | SQLException e) {
//      throw new DaoException("Many to many exception", e);
//    } finally {
//      connector.closeStatement(statement);
//    }
//    return results;
  }

  @Override
  public Set<Integer> getSecond(int editionId) throws DaoException {
    return get(editionId, EDITION_ID, AUTHOR_ID);
//    Set<Integer> results = new HashSet<>();
//    String sql = String.format(SQL, EDITION_ID);
//    ConnectionWrapper connector = getConnector();
//    PreparedStatement statement = null;
//    ResultSet resultSet;
//    try {
//      statement = connector.obtainPreparedStatement(sql);
//      statement.setInt(1, editionId);
//      resultSet = statement.executeQuery();
//      while (resultSet.next()) {
//        results.add(resultSet.getInt(AUTHOR_ID));
//      }
//    } catch (DaoException | SQLException e) {
//      throw new DaoException("Many to many exception", e);
//    } finally {
//      connector.closeStatement(statement);
//    }
//    return results;
  }

  private Set<Integer> get(int id, String columnName1, String columnName2) throws DaoException {
    Set<Integer> results = new HashSet<>();
    String sql = String.format(SQL, columnName1);
    ConnectionWrapper connector = getConnector();
    PreparedStatement statement = null;
    ResultSet resultSet;
    try {
      statement = connector.obtainPreparedStatement(sql);
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        results.add(resultSet.getInt(columnName2));
      }
    } catch (DaoException | SQLException e) {
      throw new DaoException("Many to many exception", e);
    } finally {
      connector.closeStatement(statement);
    }
    return results;
  }

  @Override
  public void bind(int editionId, int authorId) throws DaoException {
    ConnectionWrapper connector = getConnector();
    PreparedStatement statement = null;
    try {
      statement = connector.obtainPreparedStatement(BIND);
      statement.setInt(1, editionId);
      statement.setInt(2, authorId);
      System.out.println(statement);
      statement.executeUpdate();
    } catch (DaoException | SQLException e) {
      throw new DaoException("Many to many exception while binding", e);
    } finally {
      connector.closeStatement(statement);
    }
  }
}
