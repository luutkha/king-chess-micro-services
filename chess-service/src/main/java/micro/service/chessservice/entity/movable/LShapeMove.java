package micro.service.chessservice.entity.movable;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.Move;

import java.util.HashSet;
import java.util.List;
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

        this.movablePosition.add(new Square(position.getX() + 1, position.getY() + 2));
        this.movablePosition.add(new Square(position.getX() + 1, position.getY() - 2));
        this.movablePosition.add(new Square(position.getX() + 2, position.getY() + 1));
        this.movablePosition.add(new Square(position.getX() + 2, position.getY() - 1));

        this.movablePosition.removeIf(e -> e.getY() > 8 || e.getY() < 1 || e.getX() < 1 || e.getX() > 8);
    }

    @Override
    public Set<Square> addMovablePosition(Set<Square> movablePosition, List<Chess> chessMaps) {
//        log.info("addMovablePosition");
        this.setPossiblePositionDefault();
        this.movablePosition.addAll(super.addMovablePosition(this.movablePosition, chessMaps));
        return this.movablePosition;
    }
}
