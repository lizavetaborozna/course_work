package dao;

import model.Order;
import model.OrderItem;

import java.util.List;

public interface OrderDAO {
    boolean addToOrder(int userId, List<Integer> booksId, Double price);

    List<Order> getOrders(List<OrderItem> orderItemList);

    List<OrderItem> getOrderItemForUser(int userId);

    void deleteOrder(int orderId);

    List<OrderItem> getOrderItemForAllUser();

    void updateStatusOrder(int orderId);
}
