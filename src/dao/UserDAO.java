package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    boolean addUser(User user);

    User getById(Integer id);

    List<User> findAll();

    void update(User user);

    void delete(Integer id);

    User getByUserName(String username);

}
