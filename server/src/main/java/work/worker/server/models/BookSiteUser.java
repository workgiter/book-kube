package work.worker.server.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@Table(name = "book_user")
@IdClass(BookSiteUserId.class)
public class BookSiteUser {
    /**book key. */
    @Id
    @Column(name = "book_isbn")
    private String bookIsbn;

    /**book key. */
    @Id
    @Column(name = "added_by_username")
    private String addedByUsername;
}
