package com.github.fabriciolfj.dataadvanced.domain.repository;

import com.github.fabriciolfj.dataadvanced.domain.entity.Author;
import com.github.fabriciolfj.dataadvanced.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("Delete from Book b where b.author.id = ?1")
    int deleteByAuthorIdentifier(final Long id);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("Delete from Book b where b.author in ?1")
    int deleteBullByAuthors(final List<Author> authors);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("Delete from Book b where b.author.id in ?1")
    int deleteBullByAuthorsIds(final List<Long> ids);
}
