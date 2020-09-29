package com.github.fabriciolfj.dataadvanced.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
        name = "author-books-publisher-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "books", subgraph = "publisher-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(name = "publisher-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("publisher")
                        })
        }
)
public class Author implements Serializable {

    private static final long serialVersionUID = 4225172803841161426L;

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Include
    private String name;
    @ToString.Include
    @Basic(fetch = FetchType.LAZY)
    private String genre;
    @Basic(fetch = FetchType.LAZY)
    @ToString.Include
    private int age;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", orphanRemoval = true)
    private List<Book> books;

    public void addBook(final Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }

        books.add(book);
        book.setAuthor(this);
    }

    public void removeBooks() {
        books.clear();
    }

    public void removeBook(final Book book) {
        book.setAuthor(null);
        this.books.remove(book);
    }
}
