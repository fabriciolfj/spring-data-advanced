package com.github.fabriciolfj.dataadvanced.domain.repository;

import com.github.fabriciolfj.dataadvanced.domain.dto.AuthorNameAge;
import com.github.fabriciolfj.dataadvanced.domain.entity.Author;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("Delete from Author a where a.id = ?1")
    int deleteByIdentifier(final Long id);

    List<Author> findByAge(int age);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("Delete from Author a where a.id in ?1")
    int deleteByIdentifierIds(final List<Long> ids);

    @EntityGraph(value = "author-books-graph", type = EntityGraph.EntityGraphType.FETCH)
    List<Author> findByAgeLessThanOrderByNameDesc(int age);

    @EntityGraph(value = "author-books-graph", type = EntityGraph.EntityGraphType.FETCH)
    List<Author> findAll(Specification spec);

    //uma abordagem para resolver a questão do carregamento preguicoso, o que estão dentro do são tratados como eager e o restante como lazy (EntityGraph.EntityGraphType.FETCH),
    // no caso do EntityGraph.EntityGraphType.LOAD, os demais são tratados conforme especificado no FetchType
    //@EntityGraph(attributePaths = {"books"}, type = EntityGraph.EntityGraphType.FETCH)
    @EntityGraph(value = "author-books-publisher-graph", type = EntityGraph.EntityGraphType.LOAD) //pode usar a opção acima tb, mas ai não precisa da  anotação na entidade
    @Query(value = "Select a From Author a where a.age > 20 and a.age < 40")
    List<Author> find20Ate40();

    @EntityGraph(value = "author-books-publisher-graph", type = EntityGraph.EntityGraphType.LOAD)
    List<Author> findByAgeGreaterThanAndGenre(int age, String genre);

    //@Query("Select a.name, a.age From Author a where a.genre=?1 LIMIT 2") posso usar assim
    List<AuthorNameAge> findFirst2ByGenre(String genre);
}
