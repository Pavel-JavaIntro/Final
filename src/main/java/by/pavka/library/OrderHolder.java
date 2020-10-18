package by.pavka.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderHolder {
  private static final OrderHolder instance = new OrderHolder();
  private List<BookOrder> orders;

  private OrderHolder() {
    orders = new ArrayList<>();
  }

  public static OrderHolder getInstance() {
    return instance;
  }

  public void addOrder(BookOrder order) {
    orders.add(order);
  }
}
