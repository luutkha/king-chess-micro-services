package micro.service.chessservice.entity.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.ChessBoard;

@Getter
@Setter
public class MoveAChessRequest {

    @NotNull
    private ChessBoard chessBoard;
    @NotNull
    private ChessUnitConstant type;
    @NotNull
    private SideConstant side;
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
