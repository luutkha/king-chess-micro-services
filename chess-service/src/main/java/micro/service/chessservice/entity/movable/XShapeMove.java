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
import java.util.stream.Stream;

@Getter
@Setter
//@AllArgsConstructor
@Log4j2
public class XShapeMove extends MovableDecorator {
    private final Square position;
    private Set<Square> movablePosition;

    public XShapeMove(Move move, Square position) {
        super(move);
        this.position = position;
        this.movablePosition = new HashSet<>();
    }

    //TODO break position maybe a enemy --> can add to Movable Position
    private void setPossiblePositionDefault(List<Chess> chessMaps) {
        for (int i = ChessBoardConstant.MIN_X; i <= ChessBoardConstant.MAX_X - position.getX(); i++) {

            if ((position.getY() + i) <= ChessBoardConstant.MAX_Y) {
                Square newPosition = new Square(position.getX() + i, position.getY() + i);
                if (chessMaps.stream().anyMatch(e -> e.getPosition().equals(newPosition))) {
                    break;
                }
                this.movablePosition.add(new Square(position.getX() + i, position.getY() + i));

            }
            if ((position.getY() - i) >= ChessBoardConstant.MIN_Y) {

                Square newPosition = (new Square(position.getX() + i, position.getY() - i));

                boolean isPrevent = chessMaps.stream().anyMatch(e -> e.getPosition().equals(newPosition));
                if (isPrevent) {

                    break;
                }
                this.movablePosition.add(new Square(position.getX() + i, position.getY() - i));

            }
        }

        for (int i = ChessBoardConstant.MIN_X; i <= position.getX() - 1; i++) {
            int finalI = i;
            if ((position.getY() + i) <= ChessBoardConstant.MAX_Y) {
                if (chessMaps.stream().anyMatch(e -> e.getPosition().equals(new Square(position.getX() - finalI, position.getY() + finalI)))) {
                    break;
                }
                this.movablePosition.add(new Square(position.getX() - i, position.getY() + i));
            }
            if ((position.getY() - i) >= ChessBoardConstant.MIN_Y) {
                if (chessMaps.stream().anyMatch(e -> e.getPosition().equals(new Square(position.getX() - finalI, position.getY() - finalI)))) {
                    break;
                }
                this.movablePosition.add(new Square(position.getX() - i, position.getY() - i));
            }
        }
    }

    @Override
    public Set<Square> addMovablePosition(Set<Square> movablePosition, List<Chess> chessMaps) {
        this.setPossiblePositionDefault(chessMaps);
        this.movablePosition.addAll(super.addMovablePosition(this.movablePosition, chessMaps));
        return this.movablePosition;
    }
}
