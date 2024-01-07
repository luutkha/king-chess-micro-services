package micro.service.chessservice.entity.movable;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.Move;

import java.util.HashSet;
import java.util.Set;

@Log4j2
public class LShapeMove extends MovableDecorator {
    private final Square position;
    private Set<Square> movablePosition;

    public LShapeMove(Move move, Square position) {
        super(move);
        this.position = position;
        this.movablePosition = new HashSet<>();
    }

    private void setPossiblePositionDefault() {
        this.movablePosition.add(new Square(0, 0));

    }

    @Override
    public Set<Square> addMovablePosition(Set<Square> movablePosition) {
        log.info("addMovablePosition");
        this.setPossiblePositionDefault();
        this.movablePosition.addAll(super.addMovablePosition(this.movablePosition));
        return this.movablePosition;
    }
}
