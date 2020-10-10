package by.pavka.library.model.mapper;

import by.pavka.library.entity.EntityExtractor;
import by.pavka.library.entity.impl.*;

public enum TableEntityMapper {

  GENRE("genres", new EntityExtractor<Genre>() {
    @Override
    public Genre extractEntity() {
      return new Genre();
    }
  }),
  LOCATION("locations", new EntityExtractor<Location>() {
    @Override
    public Location extractEntity() {
      return new Location();
    }
  }),
  ROLE("roles", new EntityExtractor<Role>() {
    @Override
    public Role extractEntity() {
      return new Role();
    }
  }),
  OPERATION("operations", new EntityExtractor<Operation>() {
    @Override
    public Operation extractEntity() {
      return new Operation();
    }
  }),
  AUTHOR("authors", new EntityExtractor<Author>() {
    @Override
    public Author extractEntity() {
      return new Author();
    }
  }),
  EDITION("editions", new EntityExtractor<Edition>() {
    @Override
    public Edition extractEntity() {
      return new Edition();
    }
  }),
  BOOK("books", new EntityExtractor<Book>() {
    @Override
    public Book extractEntity() {
      return new Book();
    }
  }),
  USER("users", new EntityExtractor<User>() {
    @Override
    public User extractEntity() {
      return new User();
    }
  }),
  TRANSACTION("users", new EntityExtractor<Transaction>() {
    @Override
    public Transaction extractEntity() {
      return new Transaction();
    }
  });

  private final String tableName;
  private final EntityExtractor extractor;

  TableEntityMapper(String tableName, EntityExtractor extractor) {
    this.tableName = tableName;
    this.extractor = extractor;
  }

  public String getTableName() {
    return tableName;
  }

  public EntityExtractor getExtractor() {
    return extractor;
  }
}
