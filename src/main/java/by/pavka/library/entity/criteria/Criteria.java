package by.pavka.library.entity.criteria;

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
