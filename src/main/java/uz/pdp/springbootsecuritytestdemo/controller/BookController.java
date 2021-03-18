package uz.pdp.springbootsecuritytestdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootsecuritytestdemo.payload.ApiResponse;
import uz.pdp.springbootsecuritytestdemo.payload.BookDto;
import uz.pdp.springbootsecuritytestdemo.service.BookService;
import uz.pdp.springbootsecuritytestdemo.utills.ApplicationConstant;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/saveBook")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public HttpEntity<?> saveBook(@RequestBody BookDto dto){
        ApiResponse apiResponse = bookService.saveBook(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/editBook")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public HttpEntity<?> editBook(@RequestBody BookDto dto){
        ApiResponse apiResponse = bookService.editBook(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @GetMapping("/getOne/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_CLIENT')")
    public HttpEntity<?> getOne(@PathVariable(value = "id") Integer id){
        ApiResponse apiResponse = bookService.getOneBook(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_CLIENT')")
    public HttpEntity<?> allBook(@RequestParam(defaultValue = ApplicationConstant.DEFAULT_PAGE_NUMBER) Integer page,
                                 @RequestParam(defaultValue = ApplicationConstant.DEFAULT_PAGE_SIZE) Integer size) throws IllegalAccessException {
        ApiResponse apiResponse = bookService.getAllBooks(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public HttpEntity<?> deleteBook(@PathVariable(value = "id") Integer id){
        ApiResponse apiResponse = bookService.deleteBook(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
