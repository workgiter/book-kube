package work.worker.server.models;

import java.util.Set;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "users")
public class SiteUser {
    /** Unique ID of user. */
    @Id
    private String username;

    /** set of books added to DB by user. */
    @ManyToMany(mappedBy = "addedBy", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Book> book;
}
