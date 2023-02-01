package work.worker.server.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="book_name")
    private String bookName;

    // @Column(name="author_name")
    // private String authorName;

    @Lob
    @Column(name="description", columnDefinition="TEXT")
    private String description;

    @Column(name="page_count")
    private int pageCount;

    @Column(name="publisher")
    private String publisher;

    @Column(name="ISBN")
    private String ISBN;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author")
    private Set<Author> writtenBy;

    //book name, author name, bood description, page number, publisher
    //publish data, cover image, ISBN, pageCount

    // public Book (
    //     Long idPram, 
    //     String bookNamePram,
    //     String authorNamePram
    // ) {
    //     this.id = idPram;
    //     this.bookName = bookNamePram;
    //     this.authorName = authorNamePram;
    // }
}
