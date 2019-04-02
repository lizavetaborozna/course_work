package servlets;

import model.OrderStatus;
import service.OrderService;
import service.OrderServiceImpl;
import dto.OrderDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminOrdersServlet extends HttpServlet {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<OrderDTO> listAllOrders = orderService.getListAllOrder();
        session.setAttribute("orderStatus", OrderStatus.statusWaiting);
        req.setAttribute("listAllOrder", listAllOrders);
        req.getRequestDispatcher("/WEB-INF/pages/adminOrders.jsp").forward(req, resp);
    }
}
