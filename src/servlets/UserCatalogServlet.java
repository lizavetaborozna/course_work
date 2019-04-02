package servlets;


import service.BookService;
import service.BookServiceImpl;
import dto.BookDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserCatalogServlet extends HttpServlet {
    private BookService bookService = BookServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookDTO> listAllBooks = bookService.getBooks();
        req.setAttribute("listBooks", listAllBooks);
        req.getRequestDispatcher("/WEB-INF/pages/userCatalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        BookDTO bookByImageName = bookService.getBookByImageName(req.getParameter("bookImageName"));
        Cart cart = Cart.get(session);
        cart.addItem(bookByImageName);
        int number = cart.getIds().size();
        session.setAttribute("number", number);
        doGet(req, resp);
    }
}