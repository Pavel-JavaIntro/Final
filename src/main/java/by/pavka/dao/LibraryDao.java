package by.pavka.dao;

import by.pavka.entity.LibraryEntity;

public abstract class LibraryDao<T extends LibraryEntity> {
  public abstract boolean add(T entity);

}
