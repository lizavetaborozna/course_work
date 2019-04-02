package servlets;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminLookingUsersServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<User> listAllUsers = userService.getListAllUser();
        req.setAttribute("listAllUsers", listAllUsers);
        System.out.println(listAllUsers);
        req.getRequestDispatcher("/WEB-INF/pages/adminUsers.jsp").forward(req, resp);
    }
}