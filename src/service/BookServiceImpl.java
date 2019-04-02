package service;

import dao.BookDAO;
import dao.BookDaoImpl;
import converter.BookConverter;
import dto.BookDTO;

import java.util.List;

public class BookServiceImpl implements BookService {
    private static BookServiceImpl instance;

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }

    private BookDAO bookDAO = BookDaoImpl.getInstance();
    private BookConverter bookConverter = new BookConverter();

    @Override
    public List<BookDTO> getBooks() {
        return bookConverter.converterBookList(bookDAO.getBooksList());
    }

    @Override
    public BookDTO getBookByImageName(String imageName) {
        return bookConverter.bookToBookDTO(bookDAO.getBookByImageName(imageName));
    }
}
