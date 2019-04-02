package converter;

import model.Book;
import dto.BookDTO;

import java.util.ArrayList;
import java.util.List;


public class BookConverter {
    public BookDTO bookToBookDTO(Book book) {
        BookDTO bookDTO = BookDTO.newBuilder()
                .idbook(book.getIdbook())
                .name(book.getName())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .price(book.getPrice())
                .imageName(book.getImageName())
                .build();
        return bookDTO;
    }

    public List<BookDTO> converterBookList(List<Book> bookList) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookList) {
            bookDTOList.add(bookToBookDTO(book));
        }
        return bookDTOList;
    }
}
