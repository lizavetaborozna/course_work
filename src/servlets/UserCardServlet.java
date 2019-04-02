package servlets;
import service.CardService;
import service.CardServiceImpl;
import dto.CardDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserCardServlet extends HttpServlet {
    private CardService cardService= CardServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<CardDTO> listCards = cardService.getListCardForUser((String)session.getAttribute("user"));
        req.setAttribute("listCards", listCards);
        req.setAttribute("sizeListCards", listCards.size());
        req.getRequestDispatcher("/WEB-INF/pages/userCard.jsp").forward(req, resp);
    }
}
