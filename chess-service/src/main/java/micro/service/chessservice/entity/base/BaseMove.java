package micro.service.chessservice.entity.base;

import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseMove implements Move {
    @Override
    public boolean moveTo() {
        return false;
    }

    @Override
    public Set<Square> addMovablePosition(Set<Square> movablePosition, List<Chess> chessMaps) {
        return new HashSet<>();
    }
}
