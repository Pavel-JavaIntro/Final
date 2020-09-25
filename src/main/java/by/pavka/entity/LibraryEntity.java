package by.pavka.entity;

public abstract class LibraryEntity {
  private final EntityField<?>[] fields;
  private int id;

  public LibraryEntity() {
    fields = listFields();
  }

  public abstract EntityField[] listFields();

  public EntityField[] getFields() {
    return fields;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public <T> void setValue(int index, T value) {
    EntityField entityField = fields[index];
    fillField(entityField, value);
  }

  public <T> void setValue(String name, T value) {
    for (EntityField field : fields) {
      if (field.getName().equals(name)) {
        fillField(field, value);
        break;
      }
    }
  }

  private <T> void fillField(EntityField<T> field, T value) {
    field.setValue(value);
  }
}
