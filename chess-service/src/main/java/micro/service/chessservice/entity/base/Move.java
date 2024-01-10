package micro.service.chessservice.entity.base;


import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;

import java.util.List;
import java.util.Set;

public interface Move {

    boolean moveTo();

    Set<Square> addMovablePosition(Set<Square> movablePosition, List<Chess> chessMaps);
}
