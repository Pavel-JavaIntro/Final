package by.pavka.entity.impl;

import by.pavka.entity.EntityField;
import by.pavka.entity.LibraryEntity;

import java.util.Date;

public class Book extends LibraryEntity {

  @Override
  public EntityField[] listFields() {
    EntityField<Integer> editionId = new EntityField<>("edition_id", "Code of Edition");
    EntityField<Integer> locationId = new EntityField<>("location_id", "Location");
    EntityField<Integer> readerId = new EntityField<>("reader_id", "Reader");
    EntityField<Integer> standardLocationId = new EntityField<>("standard_location_id",
            "Standard Location");
    EntityField<Boolean> forDelivery = new EntityField<>("for_delivery", "Delivery Allowed");
    EntityField<Date> dueDate = new EntityField<>("due_date", "Due Date");
    return new EntityField[] {editionId, forDelivery, locationId, readerId, dueDate,
            standardLocationId};
  }
}
