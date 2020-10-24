package by.pavka.library;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OrderHolder {
  private static final OrderHolder instance = new OrderHolder();
  private Queue<BookOrder> placedOrders;
  private Queue<BookOrder> preparedOrders;

  private OrderHolder() {
    placedOrders = new ConcurrentLinkedQueue<>();
    preparedOrders = new ConcurrentLinkedQueue<>();
  }

  public static OrderHolder getInstance() {
    return instance;
  }

  public Queue<BookOrder> getPlacedOrders() {
    return placedOrders;
  }

  public Queue<BookOrder> getPreparedOrders() {
    return preparedOrders;
  }

  public void addOrder(BookOrder order) {
    placedOrders.add(order);
  }

  public void prepareOrder(BookOrder order) {
    placedOrders.remove(order);
    preparedOrders.add(order);
  }

  public void fulfillOrder(BookOrder order) {
    preparedOrders.remove(order);
  }
}
