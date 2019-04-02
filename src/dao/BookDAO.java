package dao;

import model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBooksList();

    Book getBookByImageName(String bookImageName);

    Book getBookByID(Integer id);
}
