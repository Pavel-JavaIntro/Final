package by.pavka.entity;

public class EntityField<T> {
  private final String name;
  private final String description;
  private T value;

  public EntityField(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }
}
