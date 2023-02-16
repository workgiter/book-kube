package work.worker.server.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BookSiteUserId implements Serializable {
    /**asdf. */
    private String bookIsbn;
    /**asdf. */
    private String addedByUsername;

    // default constructor


    // equals() and hashCode()
}
