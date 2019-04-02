package servlets;

import model.UserRole;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthorizationServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (userService.isUserValid(username, password)) {
            String role = userService.getUserRole(username);
            switch (role) {
                case UserRole.ADMIN:
                    session.setAttribute("user", username);
                    session.setAttribute("role", role);
                    resp.sendRedirect("/admin");
                    break;
                case UserRole.USER:
                    session.setAttribute("user", username);
                    session.setAttribute("role", role);
                    resp.sendRedirect("/user");
                    break;
                default:
                    resp.sendRedirect("/");
            }
        } else {
            resp.sendRedirect("/");
        }
    }
}
