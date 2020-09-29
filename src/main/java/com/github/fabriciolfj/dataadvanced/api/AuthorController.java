package com.github.fabriciolfj.dataadvanced.api;

import com.github.fabriciolfj.dataadvanced.domain.dto.AuthorNameAge;
import com.github.fabriciolfj.dataadvanced.domain.entity.Author;
import com.github.fabriciolfj.dataadvanced.domain.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/author")
@RestController
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/entreIdade")
    public List<Author> find20Ate40() {
        return authorService.find20Ate40();
    }

    @DeleteMapping
    public void deleteCascade() {
        authorService.deleteViaCascadeRemove();
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorId(@PathVariable("id") final Long id) {
        authorService.deleteViaIdentifiers(id);
    }

    @DeleteMapping("/{id}/book")
    public int deleteBookAuthorId(@PathVariable("id") final Long id) {
        return authorService.deleteByAuthorIdentifier(id);
    }

    @DeleteMapping("/age")
    public void deleteAuthorsAge34() {
        authorService.deleteBathAuthorsBookAge34();
    }

    @DeleteMapping("/ids")
    public void deletePorListIds() {
        authorService.deleteBathAuthorsBookIds();
    }

    @GetMapping("/idade-genero/{age}/{genero}")
    public void getIdadeGenero(@PathVariable int age, @PathVariable String genero) {
        authorService.findAgeGenero(age, genero);
    }

    /*@GetMapping("/valores")
    public void getValoresBooks() {
        authorService.fetchAuthorWithCheapBooks();
        authorService.fetchAuthorWithRestOfBooks();
    }*/

    @GetMapping("/name-age/{genre}")
    public List<AuthorNameAge> getNameAge(@PathVariable("genre")final String genre) {
        return this.authorService.findAuthorNameAge(genre);
    }

}
