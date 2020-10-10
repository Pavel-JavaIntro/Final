package by.pavka.library.entity;

public interface EntityExtractor<T extends LibraryEntity> {
  public abstract T extractEntity();
}
