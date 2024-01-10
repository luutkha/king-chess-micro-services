package micro.service.chessservice.entity.chess;

import lombok.Data;
import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.BaseMove;
import micro.service.chessservice.entity.movable.MovableDecorator;
import micro.service.chessservice.entity.movable.PlusShapeMove;
import micro.service.chessservice.entity.movable.XShapeMove;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Queen extends Chess {
    private final ChessUnitConstant type = ChessUnitConstant.QUEEN;

    public Queen(SideConstant sideConstant, Square position, Set<Square> possibleMoves) {
        super(ChessUnitConstant.QUEEN, sideConstant, position, possibleMoves);
    }

    public Queen() {
    }

    public Set<Square> generatePossibleMoves(Set<Square> possibleMoves, List<Chess> chessMaps) {
        MovableDecorator baseMove = new MovableDecorator(new BaseMove());
        MovableDecorator xMove = new XShapeMove(baseMove, this.getPosition());
        MovableDecorator plusMove = new PlusShapeMove(xMove, this.getPosition());
        super.setPossibleMoves(plusMove.addMovablePosition(new HashSet<>(), chessMaps));
        return this.getPossibleMoves();
    }
}
