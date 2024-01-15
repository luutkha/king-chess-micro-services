package micro.service.chessservice.entity.chess;

import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.BaseMove;
import micro.service.chessservice.entity.movable.MovableDecorator;
import micro.service.chessservice.entity.movable.ThePawnMove;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends Chess {
    public Pawn(SideConstant sideConstant, Square position, Set<Square> possibleMoves) {
        super(ChessUnitConstant.PAWN, sideConstant, position, possibleMoves);
    }

    public Pawn() {
    }

    public Set<Square> generatePossibleMoves(Set<Square> possibleMoves, List<Chess> chessMaps) {
        MovableDecorator baseMove = new MovableDecorator(new BaseMove());
        MovableDecorator thePawnMove = new ThePawnMove(baseMove, this.getPosition(), this.getSide());
        super.setPossibleMoves(thePawnMove.addMovablePosition(new HashSet<>(), chessMaps));
        return this.getPossibleMoves();
    }

}