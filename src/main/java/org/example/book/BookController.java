package org.example.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.book.dto.SwaggerInterface;
import org.example.book.dto.req.ReqRegisterBook;
import org.example.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "도서", description = "도서 관련 API")
@RestController()
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService service;

    @SwaggerInterface.GetBookList
    @GetMapping("")
    public List<BookEntity> getBookList(Authentication auth) {
        long userId = Long.parseLong(auth.getName());
        
        return service.getBooks(userId);
    }

    @SwaggerInterface.GetBook
    @GetMapping("{id}")
    public BookEntity getBook(@PathVariable("id") long id, Authentication auth) {
        long userId = Long.parseLong(auth.getName());
        
        return service.getBook(id, userId);
    }

    @SwaggerInterface.RegisterBook
    @PostMapping("")
    public void registerBook(@RequestBody ReqRegisterBook payload, Authentication auth) {
        long userId = Long.parseLong(auth.getName());

        service.registerBook(payload, userId);
    }

    @SwaggerInterface.ModifyBook
    @PutMapping("{id}")
    public void modifyBook(@PathVariable("id") long id, @RequestBody ReqRegisterBook payload, Authentication auth) {
        long userId = Long.parseLong(auth.getName());

        service.modifyBook(id, payload, userId);
    }
}
