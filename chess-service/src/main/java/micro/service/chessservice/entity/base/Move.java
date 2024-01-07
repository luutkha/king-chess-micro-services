package micro.service.chessservice.entity.base;


import micro.service.chessservice.entity.Square;

import java.util.Set;

public interface Move {

    boolean moveTo();

    Set<Square> addMovablePosition(Set<Square> movablePosition);
}
