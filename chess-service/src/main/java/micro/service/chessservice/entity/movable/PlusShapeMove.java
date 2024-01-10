package micro.service.chessservice.entity.movable;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.Move;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
public class PlusShapeMove extends MovableDecorator {
    private final Square position;
    private Set<Square> movablePosition;

    public PlusShapeMove(Move move, Square position) {
        super(move);
        this.position = position;
        this.movablePosition = new HashSet<>();
    }

    private void setPossiblePositionDefault() {

    }

    @Override
    public Set<Square> addMovablePosition(Set<Square> movablePosition, List<Chess> chessMaps) {
        this.setPossiblePositionDefault();
        this.movablePosition.addAll(super.addMovablePosition(this.movablePosition, chessMaps));
        return this.movablePosition;
    }
}
