package work.worker.server.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name="authors")
public class Author {
    public Author(String string) {
        authorName = string;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    @ManyToMany(mappedBy = "writtenBy", cascade = CascadeType.ALL)
    private Set<Book> wroteBook;
    
}
