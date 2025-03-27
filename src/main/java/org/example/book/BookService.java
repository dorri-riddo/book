package org.example.book;

import org.example.book.dto.req.ReqRegisterBook;
import org.example.entity.BookEntity;
import org.example.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    public void registerUser(ReqRegisterBook payload, long userId) {
        BookEntity createBookEntity = payload.toCreateBookEntity(userId);

        bookRepo.save(createBookEntity);
    }
}