package by.pavka.entity.criteria;

import by.pavka.entity.criteria.EntityField;

import java.util.ArrayList;
import java.util.List;

public class Criteria {
  List<EntityField<?>> constraints = new ArrayList<>();

  public EntityField<?> getConstraint(int index) {
    return constraints.get(index);
  }

  public void addConstraint(EntityField<?> field) {
    constraints.add(field);
  }

  public int size() {
    return constraints.size();
  }
}
