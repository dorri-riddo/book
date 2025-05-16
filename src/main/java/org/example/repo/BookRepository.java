package org.example.repo;

import jakarta.transaction.Transactional;
import org.example.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Transactional
    BookEntity save(BookEntity payload);

    BookEntity findByIdAndUserIdAndDeletedAtIsNull(long id, long userId);

    List<BookEntity> findAllByUserIdAndDeletedAtIsNullOrderByIdDesc(long userId);

    @Transactional
    @Modifying
    @Query("UPDATE books b SET b.title = :#{#book.title}, b.publisher = :#{#book.publisher}, b.isbn = :#{#book.isbn}, b.author = :#{#book.author}, " +
            "b.coverImageUrl = :#{#book.coverImageUrl}, b.totalPage = :#{#book.totalPage}, b.updatedAt = CURRENT_TIMESTAMP WHERE b.id = :id")
    int updateById(@Param("id") long id, @Param("book") BookEntity book);
}
