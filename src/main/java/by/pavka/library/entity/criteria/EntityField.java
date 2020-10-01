package by.pavka.library.entity.criteria;

public class EntityField<T> {
  private final String name;
  private T value;

  public EntityField(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }
}
