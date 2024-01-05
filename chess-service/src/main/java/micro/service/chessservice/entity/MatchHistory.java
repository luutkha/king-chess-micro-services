package micro.service.chessservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.service.chessservice.constant.ChessUnit;
import micro.service.chessservice.constant.Side;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "match_history")
@Entity
public class MatchHistory {

    private String gameId;
    @Id
    private Long id;
    @NotNull
    private Integer step;
    @NotNull
    private ChessUnit chessUnit;
    @NotNull
    private Side side;
    @Min(1)
    @Max(8)
    @NotNull
    private Integer newPositionX;
    @Min(1)
    @NotNull
    @Max(8)
    private Integer newPositionY;
    @Min(1)
    @NotNull
    @Max(8)
    private Integer currentPositionX;
    @Min(1)
    @NotNull
    @Max(8)
    private Integer currentPositionY;


}
