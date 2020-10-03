package by.pavka.library.model.mapper;

import by.pavka.library.entity.EntityFactory;
import by.pavka.library.entity.impl.Genre;
import by.pavka.library.entity.impl.Location;
import by.pavka.library.entity.impl.Operation;
import by.pavka.library.entity.impl.Role;

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

  public <T> EntityFactory getFactory() {
    return factory;
  }
}
