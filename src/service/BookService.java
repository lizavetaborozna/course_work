package service;

import  dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getBooks();

    BookDTO getBookByImageName(String imageName);
}
