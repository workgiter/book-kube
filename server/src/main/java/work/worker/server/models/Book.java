package work.worker.server.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
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
    /**unique id of book. */
    @Id
    @Column(name = "ISBN")
    private String iSBN;

    /**name of book. */
    @Column(name = "book_name")
    private String bookName;

    // /**description of book. */
    // @Lob
    // @Column(name = "description", columnDefinition = "TEXT")
    // private String description;

    /**page count of book. */
    @Column(name = "page_count")
    private int pageCount;

    /**publisher of book. */
    @Column(name = "publisher")
    private String publisher;

    /**set of authors who wrote the book. */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author")
    @JsonManagedReference
    private Set<Author> writtenBy;

    //book name, author name, bood description, page number, publisher
    //publish data, cover image, ISBN, pageCount

}
