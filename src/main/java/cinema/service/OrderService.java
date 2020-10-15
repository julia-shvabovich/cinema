package cinema.service;

import cinema.model.Order;
import cinema.model.Ticket;
import cinema.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
