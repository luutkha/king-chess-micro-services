package micro.service.chessservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "match_history", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"game_id", "step"})
})
@Entity
@Builder
//@IdClass(MatchHistoryId.class)
public class MatchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "game_id")
    private Integer gameId;
    private Integer step;
    @NotNull
    private ChessUnitConstant chessUnitConstant;
    @NotNull
    private SideConstant sideConstant;
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
