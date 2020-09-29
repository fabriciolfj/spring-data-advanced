package com.github.fabriciolfj.dataadvanced.domain.service;

import com.github.fabriciolfj.dataadvanced.domain.dto.AuthorNameAge;
import com.github.fabriciolfj.dataadvanced.domain.entity.Author;
import com.github.fabriciolfj.dataadvanced.domain.repository.AuthorRepository;
import com.github.fabriciolfj.dataadvanced.domain.repository.BookRepository;
import com.github.fabriciolfj.dataadvanced.domain.repository.specs.AuthorSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.fabriciolfj.dataadvanced.domain.repository.specs.AuthorSpecs.isAgeGt45;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Transactional
    public void deleteViaCascadeRemove() {
        authorRepository.deleteAll();;
    }

    @Transactional
    public void deleteViaIdentifiers(final Long id) {
        var author = authorRepository.findById(id).orElseThrow(RuntimeException::new);
        bookRepository.deleteByAuthorIdentifier(id);
        authorRepository.deleteInBatch(List.of(author));
    }

    public int deleteByAuthorIdentifier(final Long id) {
        return bookRepository.deleteByAuthorIdentifier(id);
    }

    public void deleteBathAuthorsBookAge34() {
        var authors = authorRepository.findByAge(34);
        bookRepository.deleteBullByAuthors(authors);
        authorRepository.deleteInBatch(authors);
    }

    @Transactional
    public void deleteBathAuthorsBookIds() {
        bookRepository.deleteBullByAuthorsIds(List.of(1L,2L,3L));
        authorRepository.deleteByIdentifierIds(List.of(1L,2L,3L));
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll(isAgeGt45());
    }

    @Transactional(readOnly = true)
    public List<Author> find20Ate40() {
        return authorRepository.find20Ate40();
    }

    @Transactional(readOnly = true)
    public void findAgeGenero(int age, String genero) {
        authorRepository.findByAgeGreaterThanAndGenre(age, genero).stream()
        .forEach(a -> System.out.println(a.getBooks().size()));
    }

    /*@Transactional(readOnly = true)
    public void fetchAuthorWithCheapBooks() {
        Author author = authorRepository.findById(1L).orElseThrow();
        author.getCheapBooks().stream().forEach(b -> System.out.println(b));
    }

    @Transactional(readOnly = true)
    public void fetchAuthorWithRestOfBooks() {
        Author author = authorRepository.findById(1L).orElseThrow();
        author.getRestBooks().stream().forEach(b -> System.out.println(b));
    }*/

    public List<AuthorNameAge> findAuthorNameAge(final String genre) {
        return this.authorRepository.findFirst2ByGenre(genre);
    }

}
