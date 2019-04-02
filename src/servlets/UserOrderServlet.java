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

public class UserOrderServlet extends HttpServlet {
    OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("orderStatus", OrderStatus.statusWaiting);
        List<OrderDTO> listOrders = orderService.getListOrderForUser((String) session.getAttribute("user"));
        req.setAttribute("sizeListOrders", listOrders.size());
        req.setAttribute("listOrders", listOrders);
        req.getRequestDispatcher("/WEB-INF/pages/userOrders.jsp").forward(req, resp);
    }
}
