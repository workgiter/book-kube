package work.worker.server.models;

import java.util.Set;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityScan
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
    private String isbn;

    /**name of book. */
    @Column(name = "book_name")
    private String bookName;

    /**page count of book. */
    @Column(name = "page_count")
    private int pageCount;

    /**publisher of book. */
    @Column(name = "publisher")
    private String publisher;

    /**cover of book. */
    @OneToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_join", referencedColumnName = "coverID")
    @JsonSerialize(using = CustomListSerializer.class)
    private CoverImg coverImg;

    /**set of authors who wrote the book. */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author")
    @JsonManagedReference
    private Set<Author> writtenBy;

    /**set of user who added the book. */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "book_user")
    @JsonManagedReference
    private Set<SiteUser> addedBy;

    //book name, author name, bood description, page number, publisher
    //publish data, cover image, ISBN, pageCount

}
