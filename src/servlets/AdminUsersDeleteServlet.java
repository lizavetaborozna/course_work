package servlets;

import service.OrderService;
import service.OrderServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminUsersDeleteServlet  extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.deleteUser(Integer.parseInt(req.getParameter("idForDelete")));
        resp.sendRedirect("/admin/users");
    }
}
