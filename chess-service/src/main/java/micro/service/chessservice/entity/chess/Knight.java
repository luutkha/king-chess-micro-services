package micro.service.chessservice.entity.chess;

import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.BaseMove;
import micro.service.chessservice.entity.movable.MovableDecorator;
import micro.service.chessservice.entity.movable.XShapeMove;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knight extends Chess {
    public Knight(SideConstant sideConstant, Square position, Set<Square> possibleMoves) {
        super(ChessUnitConstant.KNIGHT, sideConstant, position, possibleMoves);
    }

    public Knight() {
    }

    public Set<Square> generatePossibleMoves(Set<Square> possibleMoves, List<Chess> chessMaps) {
        MovableDecorator baseMove = new MovableDecorator(new BaseMove());
        MovableDecorator xMove = new XShapeMove(baseMove, this.getPosition());
        super.setPossibleMoves(xMove.addMovablePosition(new HashSet<>(), chessMaps));
        return this.getPossibleMoves();
    }

}