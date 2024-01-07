package micro.service.chessservice.entity.movable;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.constant.ChessBoardConstant;
import micro.service.chessservice.entity.base.Move;
import micro.service.chessservice.entity.Square;

import java.util.HashSet;
import java.util.Set;

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

    private void setPossiblePositionDefault() {
        for (int i = ChessBoardConstant.MIN_X; i <= ChessBoardConstant.MAX_X - position.getX(); i++) {
            if ((position.getY() + i) <= ChessBoardConstant.MAX_Y) {
                this.movablePosition.add(new Square(position.getX() + i, position.getY() + i));
            }
            if ((position.getY() - i) >= ChessBoardConstant.MIN_Y) {
                this.movablePosition.add(new Square(position.getX() + i, position.getY() - i));
            }
        }
        for (int i = position.getX() - 1; i >= ChessBoardConstant.MIN_X; i--) {
            if ((position.getY() + i) <= ChessBoardConstant.MAX_Y)
                this.movablePosition.add(new Square(position.getX() - i, position.getY() + i));
            if ((position.getY() - i) >= ChessBoardConstant.MIN_Y)
                this.movablePosition.add(new Square(position.getX() - i, position.getY() - i));
        }
    }

    @Override
    public Set<Square> addMovablePosition(Set<Square> movablePosition) {
        log.info("addMovablePosition");
        this.setPossiblePositionDefault();
//        this.movablePosition.addAll(movablePosition);
        this.movablePosition.addAll(super.addMovablePosition(this.movablePosition));
        return this.movablePosition;
    }
}
