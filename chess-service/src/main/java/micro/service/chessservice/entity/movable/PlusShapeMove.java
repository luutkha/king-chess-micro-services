package micro.service.chessservice.entity.movable;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.constant.ChessBoardConstant;
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

    private void setPossiblePositionDefault(List<Chess> chessMaps) {

        for (int i = ChessBoardConstant.MIN_X; i < ChessBoardConstant.MAX_X - this.position.getX(); i++) {
            if (addNewPositionByX(chessMaps, i)) break;
        }

        for (int i = ChessBoardConstant.MIN_X; i < this.position.getX(); i++) {
            if (addNewPositionByX(chessMaps, -i)) break;
        }

        for (int i = ChessBoardConstant.MIN_Y; i < ChessBoardConstant.MAX_Y - this.position.getY(); i++) {
            if (addNewPositionByY(chessMaps, i)) break;
        }
        for (int i = ChessBoardConstant.MIN_Y; i < this.position.getY(); i++) {
            if (addNewPositionByY(chessMaps, -i)) break;
        }


    }

    private boolean addNewPositionByY(List<Chess> chessMaps, int i) {
        Square newPosition = new Square(position.getX(), position.getY() + i);
        if (!newPosition.equals(this.position)) {

            this.movablePosition.add(newPosition);
            return chessMaps.stream().anyMatch(e -> e.getPosition().equals(newPosition));
        }
        return false;
    }

    private boolean addNewPositionByX(List<Chess> chessMaps, int i) {
        Square newPosition = new Square(position.getX() + i, position.getY());
        if (!newPosition.equals(this.position)) {
            this.movablePosition.add(newPosition);
//            log.error("TRUE return on addNewPositionByX ");
            return chessMaps.stream().anyMatch(e -> e.getPosition().equals(newPosition));
        }
//        log.error("FALSE return on addNewPositionByX ");
        return false;
    }

    @Override
    public Set<Square> addMovablePosition(Set<Square> movablePosition, List<Chess> chessMaps) {
        this.setPossiblePositionDefault(chessMaps);
        this.movablePosition.addAll(super.addMovablePosition(this.movablePosition, chessMaps));
        return this.movablePosition;
    }
}
