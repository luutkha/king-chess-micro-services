package micro.service.chessservice.entity.movable;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.constant.ChessBoardConstant;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.Move;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Log4j2
public class XShapeMove extends MovableDecorator {
    private final Square position;
    private Set<Square> movablePosition;

    public XShapeMove(Move move, Square position) {
        super(move);
        this.position = position;
        this.movablePosition = new HashSet<>();
    }

    //TODO Reed refactor
    private void setPossiblePositionDefault(List<Chess> chessMaps) {
        for (int i = ChessBoardConstant.MIN_X; i <= ChessBoardConstant.MAX_X - position.getX(); i++) {

            if ((position.getY() + i) <= ChessBoardConstant.MAX_Y && isXPositionValid(i)) {
                Square newPosition = new Square(position.getX() + i, position.getY() + i);
                this.movablePosition.add(newPosition);
                if (chessMaps.stream().anyMatch(e -> e.getPosition().equals(newPosition))) {
                    break;
                }
            } else {
                break;
            }

            if ((position.getY() - i) >= ChessBoardConstant.MIN_Y && isXPositionValid(i)) {

                Square newPosition = (new Square(position.getX() + i, position.getY() - i));
                this.movablePosition.add(newPosition);
                boolean isPrevent = chessMaps.stream().anyMatch(e -> e.getPosition().equals(newPosition));
                if (isPrevent) {
                    break;
                }

            } else {
                break;
            }
        }

        for (int i = ChessBoardConstant.MIN_X; i <= position.getX() - 1; i++) {
            if ((position.getY() + i) <= ChessBoardConstant.MAX_Y && isXPositionValid(-i)) {
                Square newPosition = new Square(position.getX() - i, position.getY() + i);
                this.movablePosition.add(newPosition);
                if (chessMaps.stream().anyMatch(e -> e.getPosition().equals(newPosition))) {
                    break;
                }
            } else {
                break;
            }
            if ((position.getY() - i) >= ChessBoardConstant.MIN_Y && isXPositionValid(-i)) {
                Square newPosition = new Square(position.getX() - i, position.getY() - i);
                this.movablePosition.add(newPosition);
                if (chessMaps.stream().anyMatch(e -> e.getPosition().equals(newPosition))) {
                    break;
                }
            } else {
                break;
            }
        }
    }

    private boolean isXPositionValid(int i) {
        return position.getX() + i <= ChessBoardConstant.MAX_X;
    }

    @Override
    public Set<Square> addMovablePosition(Set<Square> movablePosition, List<Chess> chessMaps) {
        this.setPossiblePositionDefault(chessMaps);
        this.movablePosition.addAll(super.addMovablePosition(this.movablePosition, chessMaps));
        return this.movablePosition;
    }
}
