package work.worker.server.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "covers")
public class CoverImg {

    /**
     * makes CoverImg.
     * @param idPram
     * @param coverPicturePram
     */
    public CoverImg(final String idPram, final byte[] coverPicturePram) {
        this.coverID = idPram;
        this.coverPicture = coverPicturePram;

    }

    /**id of cover. */
    @Id
    @Column(name = "coverID")
    private String coverID;

    /**length of coloum. */
    public static final int LENGTH = 16777214;
    /**cover of the book. */
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length = LENGTH, name = "cover_picture")
    private byte[] coverPicture;

    /**the book the cover belongs to. */
    @OneToOne(fetch = FetchType.LAZY, optional = false, mappedBy = "coverImg")
    private Book book;
}
