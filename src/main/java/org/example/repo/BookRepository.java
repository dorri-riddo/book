package org.example.repo;

import jakarta.transaction.Transactional;
import org.example.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Transactional
    BookEntity save(BookEntity payload);
}
