package org.example.book;

import org.example.book.dto.req.ReqRegisterBook;
import org.example.entity.BookEntity;
import org.example.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    public void registerUser(ReqRegisterBook payload, long userId) {
        BookEntity createBookEntity = payload.toCreateBookEntity(userId);

        bookRepo.save(createBookEntity);
    }

    public void modifyBook(long id, ReqRegisterBook payload, long userId) {
        BookEntity book = bookRepo.findByIdAndUserIdAndDeletedAtIsNull(id, userId);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found book");
        }

        BookEntity modifyBookEntity = payload.toCreateBookEntity(userId);

        bookRepo.updateById(id, modifyBookEntity);
    }
}