package by.pavka.entity;

public interface EntityFactory<T extends LibraryEntity> {
  public abstract T createEntity();
}
