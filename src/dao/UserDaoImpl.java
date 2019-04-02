package dao;

import config.ConnectionManager;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO {
    private static UserDaoImpl instance;

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    public boolean addUser(User user) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user (login, password, role) VALUES (?, ?, ?)")) {
                    statement.setString(1, user.getLogin());
                    statement.setString(2, user.getPassword());
                    statement.setInt(3, 2);
                    statement.executeUpdate();
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
        return true;
    }

    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    public User getById(Integer id) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM user WHERE id=?")) {
                    statement.setString(1, String.valueOf(id));
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        return User.newBuilder()
                                .id(resultSet.getInt("id"))
                                .login(resultSet.getString("login"))
                                .password(resultSet.getString("password"))
                                .role(resultSet.getString("role"))
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

    public User getByUserName(String username) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM user WHERE login=?")) {
                    statement.setString(1, username);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        return User.newBuilder()
                                .id(resultSet.getInt("id"))
                                .login(resultSet.getString("login"))
                                .password(resultSet.getString("password"))
                                .role(resultSet.getString("role"))
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


    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM user")) {
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        users.add(User.newBuilder()
                                .id(resultSet.getInt("id"))
                                .login(resultSet.getString("login"))
                                .password(resultSet.getString("password"))
                                .role(resultSet.getString("role"))
                                .build()
                        );
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return users;
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

    public void update(User user) {

    }

    public void delete(Integer id) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM user WHERE id=?"
                )) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
