package org.example.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.book.dto.SwaggerInterface;
import org.example.book.dto.req.ReqRegisterBook;
import org.example.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @PostMapping(value = "", consumes = "multipart/form-data")
    public void registerBook(
            @RequestPart(value = "payload") ReqRegisterBook payload,
            @RequestPart(value = "coverImage", required = false) MultipartFile coverImage,
            Authentication auth) {
        long userId = Long.parseLong(auth.getName());

        service.registerBook(payload, coverImage, userId);
    }

    @SwaggerInterface.ModifyBook
    @PutMapping(value = "{id}", consumes = "multipart/form-data")
    public void modifyBook(
            @PathVariable("id") long id,
            @RequestPart(value = "payload") ReqRegisterBook payload,
            @RequestPart(value = "coverImage", required = false) MultipartFile coverImage,
            Authentication auth) {
        long userId = Long.parseLong(auth.getName());

        service.modifyBook(id, payload, coverImage, userId);
    }
}
