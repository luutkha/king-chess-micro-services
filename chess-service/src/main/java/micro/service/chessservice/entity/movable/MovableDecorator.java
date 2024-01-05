package micro.service.chessservice.entity.movable;

import lombok.Getter;
import lombok.Setter;
import micro.service.chessservice.entity.Move;
import micro.service.chessservice.entity.Square;

import java.util.Set;

//@AllArgsConstructor
@Getter
@Setter
public class MovableDecorator implements Move {
    private Move move;
    private Set<Square> movablePosition;

    public MovableDecorator(Move move) {
        this.move = move;
    }

    @Override
    public boolean moveTo() {
        return false;
    }

    @Override
    public Set<Square> addMovablePosition(Set<Square> movablePosition) {
        this.movablePosition.addAll(movablePosition);
        return this.move.addMovablePosition(movablePosition);
    }


}
