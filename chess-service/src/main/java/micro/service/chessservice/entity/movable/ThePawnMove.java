package micro.service.chessservice.entity.movable;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.Move;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
public class ThePawnMove extends MovableDecorator {
    private final Square position;
    private SideConstant side;
    private Set<Square> movablePosition;

    public ThePawnMove(Move move, Square position,SideConstant side) {
        super(move);
        this.position = position;
        this.side = side;
        this.movablePosition = new HashSet<>();
    }

    // TODO: pawn have special case when kill other chess. implement later
    private void setPossiblePositionDefault() {
        if(side.equals(SideConstant.WHITE)) {

        this.movablePosition.add(new Square(position.getX(), position.getY() + 1));
        }
        else {
            this.movablePosition.add(new Square(position.getX(), position.getY() -1));

        }
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
