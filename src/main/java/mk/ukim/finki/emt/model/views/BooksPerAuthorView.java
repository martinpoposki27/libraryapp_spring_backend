package mk.ukim.finki.emt.model.views;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Subselect("SELECT * FROM public.books_per_author")
@Immutable
public class BooksPerAuthorView {

    @Id
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "num_books")
    private Integer numBooks;
}
