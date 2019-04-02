package dao;

import config.ConnectionManager;
import model.Book;
import model.Order;
import model.OrderItem;
import model.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDAO {
    private static OrderDaoImpl instance;

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public boolean addToOrder(int userId, List<Integer> booksId, Double price) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT  INTO bookshop.order( iduser, price, status) " +
                                "VALUES( ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS
                )) {
                    connection.setAutoCommit(false);
                    int newId;

                    preparedStatement.setInt(1, userId);
                    preparedStatement.setDouble(2, price);
                    preparedStatement.setString(3, OrderStatus.statusWaiting);
                    preparedStatement.executeUpdate();

                    ResultSet keys = preparedStatement.getGeneratedKeys();
                    keys.next();
                    newId = Integer.parseInt(keys.getString(1));

                    try (PreparedStatement preparedStatement1 = connection.prepareStatement(
                            "INSERT  INTO orderitems(idorderItems, itemId)" +
                                    " VALUES(?,?);"
                    )) {
                        for (Integer bookId : booksId) {
                            preparedStatement1.setInt(1, newId);
                            preparedStatement1.setLong(2, bookId);
                            preparedStatement1.executeUpdate();
                        }
                    }
                }
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Order> getOrders(List<OrderItem> orderItemList) {
        Connection connection = null;
        List<Order> orderList = new ArrayList<>();
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT book.name, book.price FROM orderitems, bookshop.order, book " +
                                "WHERE bookshop.order.iduser=? AND bookshop.order.idorder=? " +
                                "AND bookshop.order.idorder=orderitems.idorderItems " +
                                "AND orderitems.itemId=book.idbook;"
                )) {
                    for (OrderItem orderItem : orderItemList) {

                        preparedStatement.setInt(1, orderItem.getUserId());
                        preparedStatement.setInt(2, orderItem.getId());
                        ResultSet resultSet = preparedStatement.executeQuery();
                        List<Book> books = new ArrayList<>();
                        while (resultSet.next()) {
                            books.add(Book.newBuilder()
                                    .name(resultSet.getString("book.name"))
                                    .price(resultSet.getDouble("book.price"))
                                    .build()
                            );
                        }
                        orderList.add(Order.newBuilder()
                                .id(orderItem.getId())
                                .user(orderItem.getUser())
                                .price(orderItem.getPrice())
                                .status(orderItem.getStatus())
                                .books(books)
                                .build()
                        );
                    }
                    return orderList;
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<OrderItem> getOrderItemForUser(int userId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT DISTINCT bookshop.order.idorder, bookshop.order.price, bookshop.order.status FROM orderitems, bookshop.order, book " +
                                "WHERE bookshop.order.iduser=? AND " +
                                "bookshop.order.idorder=orderitems.idorderItems AND " +
                                "orderitems.itemId=book.idbook;"
                )) {
                    connection.setAutoCommit(false);
                    preparedStatement.setInt(1, userId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        orderItemList.add(OrderItem.newBuilder()
                                .id(resultSet.getInt("order.idorder"))
                                .userId(userId)
                                .price(resultSet.getDouble("order.price"))
                                .status(resultSet.getString("order.status"))
                                .build()
                        );
                    }
                    return orderItemList;
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public void deleteOrder(int orderId) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM orderitems WHERE orderitems.idorderItems=?;"
                )) {
                    connection.setAutoCommit(false);

                    preparedStatement.setInt(1, orderId);
                    preparedStatement.executeUpdate();
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement(
                            "DELETE FROM bookshop.order WHERE bookshop.order.idorder=?;"
                    )) {
                        preparedStatement1.setInt(1, orderId);
                        preparedStatement1.executeUpdate();
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<OrderItem> getOrderItemForAllUser() {
        List<OrderItem> orderItemList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT DISTINCT bookshop.order.idorder, bookshop.user.id," +
                                "bookshop.user.login, bookshop.order.price, bookshop.order.status " +
                                "FROM orderitems, bookshop.order, book, bookshop.user WHERE bookshop.user.id=bookshop.order.iduser" +
                                " AND bookshop.order.idorder=orderitems.idorderItems " +
                                "AND orderitems.itemId=book.idbook;"
                )) {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        orderItemList.add(OrderItem.newBuilder()
                                .id(resultSet.getInt("order.idorder"))
                                .userId(resultSet.getInt("user.id"))
                                .user(resultSet.getString("user.login"))
                                .price(resultSet.getDouble("order.price"))
                                .status(resultSet.getString("status"))
                                .build()
                        );
                    }
                    return orderItemList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public void updateStatusOrder(int orderId) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE bookshop.order SET bookshop.order.status=? WHERE bookshop.order.idorder=?;")) {
                    connection.setAutoCommit(false);
                    preparedStatement.setString(1, OrderStatus.statusCompleted);
                    preparedStatement.setInt(2, orderId);
                    preparedStatement.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}