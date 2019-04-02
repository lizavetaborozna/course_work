package servlets;

import service.OrderService;
import service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminOrderChangeServlet extends HttpServlet {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService.updateStatusOrder(Integer.parseInt(req.getParameter("change")));
        resp.sendRedirect("/admin");
    }
}
