package by.pavka.library.model.mapper;

import by.pavka.library.entity.EntityFactory;
import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.impl.*;
import by.pavka.library.model.dao.LibraryDao;
import by.pavka.library.model.dao.impl.SimpleLibraryDao;

public enum TableEntityMapper {

  GENRE("genres", new EntityFactory<Genre>() {
    @Override
    public Genre createEntity() {
      return new Genre();
    }
  }),
  LOCATION("locations", new EntityFactory<Location>() {
    @Override
    public Location createEntity() {
      return new Location();
    }
  }),
  ROLE("roles", new EntityFactory<Role>() {
    @Override
    public Role createEntity() {
      return new Role();
    }
  }),
  OPERATION("operations", new EntityFactory<Operation>() {
    @Override
    public Operation createEntity() {
      return new Operation();
    }
  }),
  AUTHOR("authors", new EntityFactory<Author>() {
    @Override
    public Author createEntity() {
      return new Author();
    }
  }),
  EDITION("editions", new EntityFactory<Edition>() {
    @Override
    public Edition createEntity() {
      return new Edition();
    }
  }),
  BOOK("books", new EntityFactory<Book>() {
    @Override
    public Book createEntity() {
      return new Book();
    }
  }),
  USER("users", new EntityFactory<User>() {
    @Override
    public User createEntity() {
      return new User();
    }
  }),
  TRANSACTION("users", new EntityFactory<Transaction>() {
    @Override
    public Transaction createEntity() {
      return new Transaction();
    }
  });

  private final String tableName;
  private final EntityFactory factory;

  TableEntityMapper(String tableName, EntityFactory factory) {
    this.tableName = tableName;
    this.factory = factory;
  }

  public String getTableName() {
    return tableName;
  }

  public EntityFactory getFactory() {
    return factory;
  }
}
