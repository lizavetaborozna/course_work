package servlets;

import dto.BookDTO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<BookDTO> cart = new ArrayList<>();

    public synchronized void addItem(BookDTO book) {
        cart.add(book);
    }

    public synchronized List<BookDTO> getIds() {
        return new ArrayList<>(cart);
    }

    public static Cart get(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
