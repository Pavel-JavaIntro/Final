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

public class EditionDao extends SimpleLibraryDao<Edition> implements ManyToManyDao<Edition, Author> {
  private static final String EDITION_ID = "edition_id";
  private static final String AUTHOR_ID = "author_id";
  private static final String SQL = "SELECT * FROM edition_author WHERE %s=?";
  public EditionDao() throws DaoException {
    super(TableEntityMapper.EDITION);
  }

  @Override
  public Set<Integer> getFirst(int authorId) throws DaoException {
    Set<Integer> results = new HashSet<>();
    String sql = String.format(SQL, AUTHOR_ID);
    ConnectionWrapper connector = getConnector();
    PreparedStatement statement = null;
    ResultSet resultSet;
    try {
      statement = connector.obtainPreparedStatement(sql);
      statement.setInt(1, authorId);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        results.add(resultSet.getInt(EDITION_ID));
      }
    } catch (DaoException | SQLException e) {
      throw new DaoException("Many to many exception", e);
    } finally {
      connector.closeStatement(statement);
    }
    return results;
  }

  @Override
  public Set<Integer> getSecond(int editionId) throws DaoException {
    Set<Integer> results = new HashSet<>();
    String sql = String.format(SQL, EDITION_ID);
    ConnectionWrapper connector = getConnector();
    PreparedStatement statement = null;
    ResultSet resultSet;
    try {
      statement = connector.obtainPreparedStatement(sql);
      statement.setInt(1, editionId);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        results.add(resultSet.getInt(AUTHOR_ID));
      }
    } catch (DaoException | SQLException e) {
      throw new DaoException("Many to many exception", e);
    } finally {
      connector.closeStatement(statement);
    }
    return results;
  }
}
