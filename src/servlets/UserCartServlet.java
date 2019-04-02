package servlets;

import dto.BookDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = Cart.get(session);
        List<BookDTO> listBooksInCart = cart.getIds();
        req.setAttribute("listCart", listBooksInCart);
        req.setAttribute("sizeListCart", listBooksInCart.size());
        req.getRequestDispatcher("/WEB-INF/pages/userCart.jsp").forward(req, resp);
    }
}
