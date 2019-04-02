package service;

import dao.UserDAO;
import dao.UserDaoImpl;
import model.User;
import converter.UserConverter;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserDAO userDAO = UserDaoImpl.getInstance();
    private UserConverter userConverter=new UserConverter();

    @Override
    public boolean isUserValid(String username, String password) {
        User user = userDAO.getByUserName(username);
        return null != user && Objects.equals(user.getPassword(), password);
    }

    @Override
    public String getUserRole(String username) {
        return userDAO.getByUserName(username).getRole();
    }

    @Override
    public List<User> getListAllUser() {
        return userDAO.findAll();
    }

    @Override
    public void deleteUser(int idUser) {
        userDAO.delete(idUser);
    }

}
