package by.pavka.library;

import by.pavka.library.model.LibraryFatalException;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OrderHolder {
  private static final OrderHolder instance = new OrderHolder();
  private Queue<BookOrder> placedOrders;
  private Queue<BookOrder> preparedOrders;

  private OrderHolder() {
    WelcomeService welcomeService = WelcomeService.getInstance();
    placedOrders = new ConcurrentLinkedQueue<>();
    preparedOrders = new ConcurrentLinkedQueue<>();
    try {
      Collection<BookOrder> oldPlacedOrders = welcomeService.getPlacedOrder();
      //placedOrders.addAll(oldPlacedOrders);
      Collection<BookOrder> oldPreparedOrders = welcomeService.getPreparedOrders();
      //preparedOrders.addAll(oldPreparedOrders);
    } catch (ServiceException e) {
      throw new LibraryFatalException("Cannot initialize order status");
    }
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
    preparedOrders.add(order);
  }

  public void fulfillOrder(BookOrder order) {
    preparedOrders.remove(order);
  }
}
