package by.pavka.library.entity;

public interface EntityFactory<T extends LibraryEntity> {
  public abstract T createEntity();
}
