package uz.pdp.springbootsecuritytestdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.springbootsecuritytestdemo.entity.Book;
import uz.pdp.springbootsecuritytestdemo.payload.ApiResponse;
import uz.pdp.springbootsecuritytestdemo.payload.BookDto;
import uz.pdp.springbootsecuritytestdemo.reposiroty.BookRepository;
import uz.pdp.springbootsecuritytestdemo.utills.CommonUtills;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public ApiResponse saveBook(BookDto dto){
        try {
            Book book = new Book();
            book.setName(dto.getName());
            book.setAuthor(dto.getAuthor());
            bookRepository.save(book);
            return new ApiResponse("Saved", true);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse editBook(BookDto dto){
        try {
            Book book = bookRepository.findById(dto.getId())
                    .orElseThrow(() -> new IllegalStateException("Book not found"));
            book.setName(dto.getName());
            book.setAuthor(dto.getAuthor());
            bookRepository.save(book);
            return new ApiResponse("Edited", true);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse getAllBooks(Integer page, Integer size) throws IllegalAccessException {
        Page<Book> books = bookRepository.findAll(CommonUtills.simplePageable(page, size));

        return new ApiResponse(
                "Ok",
                true,
                books.getTotalElements(),
                books.getTotalPages(),
                books.getContent()
        );
    }

    public ApiResponse getOneBook(Integer id){
        try {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException("book not found"));
            return new ApiResponse("Ok", true, book);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse deleteBook(Integer id){
        try {
            bookRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error", false);
        }
    }
}
