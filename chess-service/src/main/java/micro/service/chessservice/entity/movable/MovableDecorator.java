package micro.service.chessservice.entity.movable;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.Move;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@AllArgsConstructor
@Getter
@Setter
@Log4j2
//@Loggable(Loggable.INFO)
public class MovableDecorator implements Move {
    private Move move;
    private Set<Square> movablePosition;

    public MovableDecorator(Move move) {
        this.move = move;
        this.movablePosition = new HashSet<>();
    }

    @Override
    public boolean moveTo() {
        return false;
    }

    @Override
    public Set<Square> addMovablePosition(Set<Square> movablePosition, List<Chess> chessMaps) {
//        log.info("addMovablePosition");
        this.movablePosition.addAll(movablePosition);
        return this.move.addMovablePosition(this.movablePosition, chessMaps);
    }


}
