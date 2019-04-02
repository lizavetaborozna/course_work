package servlets;

import service.OrderService;
import service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserOrderDeleteServlet extends HttpServlet {
    private OrderService orderService= OrderServiceImpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService.deleteOrder(Integer.parseInt(req.getParameter("idForDelete")));
        resp.sendRedirect("/user/order");
    }
}
