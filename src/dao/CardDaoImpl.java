package dao;

import config.ConnectionManager;
import model.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDAO {
    private static CardDaoImpl instance;

    private CardDaoImpl() {
    }

    public static CardDaoImpl getInstance() {
        if (instance == null) {
            instance = new CardDaoImpl();
        }
        return instance;
    }

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    @Override
    public List<Card> getListCardForUser(Integer idUser) {
        List<Card> listCardForUser = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT cardNumber, month, year , description FROM card " +
                                "WHERE card.userId=? ;"
                )) {
                    preparedStatement.setInt(1, idUser);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        listCardForUser.add(Card.newBuilder()
                                .number(resultSet.getString("cardNumber"))
                                .month(resultSet.getInt("month"))
                                .year(resultSet.getInt("year"))
                                .description(resultSet.getString("description"))
                                .build()
                        );
                    }
                    return listCardForUser;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
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
    public boolean addCard(Card infoAboutCard, Integer idUser) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT  INTO card( userId, cardNumber, month, year, description) " +
                                "VALUES( ?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS
                )) {
                    connection.setAutoCommit(false);
                    preparedStatement.setInt(1, idUser);
                    preparedStatement.setString(2, infoAboutCard.getNumber());
                    preparedStatement.setInt(3, infoAboutCard.getMonth());
                    preparedStatement.setInt(4, infoAboutCard.getYear());
                    preparedStatement.setString(5, infoAboutCard.getDescription());
                    preparedStatement.executeUpdate();
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
}
