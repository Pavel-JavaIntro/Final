package by.pavka.model;

import by.pavka.entity.EntityFactory;
import by.pavka.entity.LibraryEntity;
import by.pavka.entity.impl.Genre;
import by.pavka.entity.impl.Location;
import by.pavka.entity.impl.Operation;
import by.pavka.entity.impl.Role;

import java.util.HashMap;
import java.util.Map;

public class TableEntityMapper {
  private static Map<String, EntityFactory<? extends LibraryEntity>> mapper;

  private TableEntityMapper() {}

  static {
    mapper = new HashMap<>();
    mapper.put("locations", new EntityFactory<Location>() {
      @Override
      public Location createEntity() {
        return new Location();
      }
    });
    mapper.put("genres", new EntityFactory<Genre>() {
      @Override
      public Genre createEntity() {
        return new Genre();
      }
    });
    mapper.put("roles", new EntityFactory<Role>() {
      @Override
      public Role createEntity() {
        return new Role();
      }
    });
    mapper.put("operations", new EntityFactory<Operation>() {
      @Override
      public Operation createEntity() {
        return new Operation();
      }
    });
  }

  public static EntityFactory<? extends LibraryEntity> getFactory(String table) {
    return mapper.get(table);
  }


}
