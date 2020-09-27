package by.pavka.model.dao;

import by.pavka.entity.criteria.Criteria;
import by.pavka.entity.criteria.EntityField;
import by.pavka.entity.LibraryEntity;

import java.util.List;

public interface LibraryDao<T extends LibraryEntity> {
  void add(T entity) throws DaoException;
  List<T> read(Criteria criteria) throws DaoException;
  List<T> read() throws DaoException;
  void remove(int id) throws DaoException;
  void update(int id, EntityField<?>... fields) throws DaoException;
  void close();


}
