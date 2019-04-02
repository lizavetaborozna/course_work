package dao;

import config.ConnectionManager;
import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDAO {
    private static BookDaoImpl instance;

    private BookDaoImpl() {
    }

    public static BookDaoImpl getInstance() {
        if (instance == null) {
            instance = new BookDaoImpl();
        }
        return instance;
    }

    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public List<Book> getBooksList() {
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM book")) {
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        books.add(Book.newBuilder()
                                .idbook(resultSet.getInt("idbook"))
                                .name(resultSet.getString("name"))
                                .author(resultSet.getString("author"))
                                .genre(resultSet.getString("genre"))
                                .price(resultSet.getDouble("price"))
                                .imageName(resultSet.getString("imageName"))
                                .build()
                        );
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return books;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public Book getBookByImageName(String bookImageName) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM book WHERE imageName=?")) {
                    statement.setString(1, bookImageName);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        return Book.newBuilder()
                                .idbook(resultSet.getInt("idbook"))
                                .name(resultSet.getString("name"))
                                .author(resultSet.getString("author"))
                                .genre(resultSet.getString("genre"))
                                .price(resultSet.getDouble("price"))
                                .imageName(resultSet.getString("imageName"))
                                .build();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public Book getBookByID(Integer id) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM book WHERE idbook=?")) {
                    statement.setInt(1, id);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        return Book.newBuilder()
                                .idbook(resultSet.getInt("idbook"))
                                .name(resultSet.getString("name"))
                                .author(resultSet.getString("author"))
                                .genre(resultSet.getString("genre"))
                                .price(resultSet.getDouble("price"))
                                .imageName(resultSet.getString("imageName"))
                                .build();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }
}
