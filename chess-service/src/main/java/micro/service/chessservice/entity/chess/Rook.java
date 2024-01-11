package micro.service.chessservice.entity.chess;

import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.BaseMove;
import micro.service.chessservice.entity.movable.MovableDecorator;
import micro.service.chessservice.entity.movable.PlusShapeMove;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rook extends Chess {

    public Rook(SideConstant sideConstant, Square position, Set<Square> possibleMoves) {
        super(ChessUnitConstant.ROOK, sideConstant, position, possibleMoves);
    }

    public Rook() {
    }

    public Set<Square> generatePossibleMoves(Set<Square> possibleMoves, List<Chess> chessMaps) {
        MovableDecorator baseMove = new MovableDecorator(new BaseMove());
        MovableDecorator plusMove = new PlusShapeMove(baseMove, this.getPosition());
        super.setPossibleMoves(plusMove.addMovablePosition(new HashSet<>(), chessMaps));
        return this.getPossibleMoves();
    }

}