package org.example.book.dto;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.book.BookService;
import org.example.book.dto.req.ReqRegisterBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "도서", description = "도서 관련 API")
@RestController()
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService service;

    @SwaggerInterface.RegisterBook
    @PostMapping("")
    public void registerBook(@RequestBody ReqRegisterBook payload, Authentication auth) {
        long userId = Long.parseLong(auth.getName());

        service.registerUser(payload, userId);
    }
}
