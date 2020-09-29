package com.github.fabriciolfj.dataadvanced;

import com.github.fabriciolfj.dataadvanced.domain.entity.Author;
import com.github.fabriciolfj.dataadvanced.domain.entity.Book;
import com.github.fabriciolfj.dataadvanced.domain.entity.Publisher;
import com.github.fabriciolfj.dataadvanced.domain.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@Slf4j
public class DataAdvancedApplication implements CommandLineRunner {

    @Autowired
    private AuthorRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DataAdvancedApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var author = Author.builder()
                .name("Joana Nimar")
                .age(29)
                .genre("Feminino")
                .build();

        var author2 = Author.builder()
                .name("Fabricio Jacob")
                .age(34)
                .genre("Masculino")
                .build();

        var author3 = Author.builder()
                .name("Suzana Penna")
                .age(34)
                .genre("Feminino")
                .build();

        var author4 = Author.builder()
                .name("Teste1")
                .age(50)
                .genre("Feminino")
                .build();

        var author5 = Author.builder()
                .name("Teste2")
                .age(48)
                .genre("Feminino")
                .build();

        var author6 = Author.builder()
                .name("Teste3")
                .age(84)
                .genre("Feminino")
                .build();

        var abril = Publisher.builder()
                .company("Abril")
                .build();

        var epoca = Publisher.builder()
                .company("epoca")
                .build();

        List.of(Book.builder()
                        .isbn("001-jn")
                        .title("A history of ancient prague")
                        .price(new BigDecimal("22.00"))
                        .build(),
                Book.builder()
                        .isbn("003-jn")
                        .title("World history")
                        .price(new BigDecimal("12.00"))
                        .build(),
                Book.builder()
                        .isbn("002-jn")
                        .title("A peaple 's history")
                        .price(new BigDecimal("18.00"))
                        .build())
		.stream().forEach(b -> {
		    b.setPublisher(abril);
		    author2.addBook(b);
        });

        List.of(Book.builder()
                        .isbn("001-jn")
                        .title("A history of ancient prague")
                        .price(new BigDecimal("22.00"))
                        .build(),
                Book.builder()
                        .isbn("003-jn")
                        .title("World history")
                        .price(new BigDecimal("23.00"))
                        .build(),
                Book.builder()
                        .isbn("002-jn")
                        .title("A peaple 's history")
                        .price(new BigDecimal("1.00"))
                        .build())
                .stream().forEach(b -> {
            b.setPublisher(epoca);
            author.addBook(b);
        });

        List.of(Book.builder()
                        .isbn("001-jn")
                        .title("A history of ancient prague")
                        .build(),
                Book.builder()
                        .isbn("003-jn")
                        .title("World history")
                        .price(new BigDecimal("55.00"))
                        .build(),
                Book.builder()
                        .isbn("002-jn")
                        .title("A peaple 's history")
                        .price(new BigDecimal("87.00"))
                        .build())
                .stream().forEach(b -> {
            author3.addBook(b);
        });

        List.of(Book.builder()
                        .isbn("001-jn")
                        .title("A history of ancient prague")
                        .build(),
                Book.builder()
                        .isbn("003-jn")
                        .title("World history")
                        .build(),
                Book.builder()
                        .isbn("002-jn")
                        .title("A peaple 's history")
                        .build())
                .stream().forEach(b -> {
            author4.addBook(b);
        });

        List.of(Book.builder()
                        .isbn("001-jn")
                        .title("A history of ancient prague")
                        .build(),
                Book.builder()
                        .isbn("003-jn")
                        .title("World history")
                        .build(),
                Book.builder()
                        .isbn("002-jn")
                        .title("A peaple 's history")
                        .build())
                .stream().forEach(b -> {
            author5.addBook(b);
        });

        List.of(Book.builder()
                        .isbn("001-jn")
                        .title("A history of ancient prague")
                        .build(),
                Book.builder()
                        .isbn("003-jn")
                        .title("World history")
                        .build(),
                Book.builder()
                        .isbn("002-jn")
                        .title("A peaple 's history")
                        .build())
                .stream().forEach(b -> {
            author6.addBook(b);
        });

        repository.saveAll(List.of(author, author2, author3, author4, author5, author6));

        repository.findAll().stream().forEach(p -> log.info(p.getId().toString()));
    }
}
