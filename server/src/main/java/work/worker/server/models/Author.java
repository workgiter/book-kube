package work.worker.server.models;

import java.util.Set;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "authors")
public class Author {
    /**
     * constructor.
     * @param authorID
     * @param name
     */
    public Author(final String authorID, final String name) {
        id = authorID;
        authorName = name;
    }

    /**unique id for author. */
    @Id
    private String id;

    /**name of author. */
    @Column(name = "author_name")
    private String authorName;

    /**the set of books the author wrote. */
    @ManyToMany(mappedBy = "writtenBy", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Book> wroteBook;

}
