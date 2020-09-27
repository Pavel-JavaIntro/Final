package by.pavka.entity.impl;

import by.pavka.entity.criteria.EntityField;
import by.pavka.entity.LibraryEntity;

import java.util.Date;

public class Book extends LibraryEntity {

  @Override
  public EntityField[] listFields() {
    EntityField<Integer> editionId = new EntityField<>("edition_id");
    EntityField<Integer> locationId = new EntityField<>("location_id");
    EntityField<Integer> readerId = new EntityField<>("reader_id");
    EntityField<Integer> standardLocationId = new EntityField<>("standard_location_id");
    EntityField<Boolean> forDelivery = new EntityField<>("for_delivery");
    EntityField<Date> dueDate = new EntityField<>("due_date");
    return new EntityField[] {editionId, forDelivery, locationId, readerId, dueDate,
            standardLocationId};
  }

}
