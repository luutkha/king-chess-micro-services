package micro.service.chessservice.entity;

import lombok.*;
import micro.service.chessservice.constant.SideConstant;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovableMap {

    private SideConstant sideConstant;
    private Integer step;
    private Integer gameId;
//    @Value(va)
    private List<ChessUnitMovable> chessUnitMovables;

}
