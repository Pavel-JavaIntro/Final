package by.pavka.library.model;

import by.pavka.library.entity.EntityFactory;
import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.impl.Genre;
import by.pavka.library.entity.impl.Location;
import by.pavka.library.entity.impl.Operation;
import by.pavka.library.entity.impl.Role;

import java.util.ArrayList;
import java.util.List;

public class TableEntityMapper {
  private static List<MapperEntry<? extends LibraryEntity>> entries;

  private TableEntityMapper() {}

  static {
    entries = new ArrayList<>();

    MapperEntry<Location> entry1 =
        new MapperEntry<>(
            "locations",
            new EntityFactory<Location>() {
              @Override
              public Location createEntity() {
                return new Location();
              }
            });
    MapperEntry<Genre> entry2 =
        new MapperEntry<>(
            "genres",
            new EntityFactory<Genre>() {
              @Override
              public Genre createEntity() {
                return new Genre();
              }
            });
    MapperEntry<Role> entry3 =
        new MapperEntry<>("roles", new EntityFactory<Role>() {
          @Override
          public Role createEntity() {
            return new Role();
          }
        }) ;
    MapperEntry<Operation> entry4 =
        new MapperEntry<>("operations", new EntityFactory<Operation>() {
          @Override
          public Operation createEntity() {
            return new Operation();
          }
        }) ;
    entries.add(entry1);
    entries.add(entry2);
    entries.add(entry3);
    entries.add(entry4);
  }

  public static MapperEntry<? extends LibraryEntity> getEntry(int index) {
    return entries.get(index);
  }

  public static class MapperEntry<T extends LibraryEntity> {
    private final String tableName;
    private final EntityFactory<T> factory;

    public MapperEntry(String tableName, EntityFactory<T> factory) {
      this.tableName = tableName;
      this.factory = factory;
    }

    public String getTableName() {
      return tableName;
    }

    public EntityFactory<T> getFactory() {
      return factory;
    }
  }
}
