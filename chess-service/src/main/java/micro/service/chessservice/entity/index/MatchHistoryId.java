package micro.service.chessservice.entity.index;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable

public class MatchHistoryId implements Serializable {
    private String gameId;
    private Integer step;

    // constructors, equals, and hashCode methods
}