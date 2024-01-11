package micro.service.chessservice.entity.chess;

import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.base.BaseMove;
import micro.service.chessservice.entity.movable.MovableDecorator;
import micro.service.chessservice.entity.movable.TheKingMove;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class King extends Chess {
    public King(SideConstant sideConstant, Square position, Set<Square> possibleMoves) {
        super(ChessUnitConstant.QUEEN, sideConstant, position, possibleMoves);
    }

    public King() {
    }

    public Set<Square> generatePossibleMoves(Set<Square> possibleMoves, List<Chess> chessMaps) {
        MovableDecorator baseMove = new MovableDecorator(new BaseMove());
        MovableDecorator kingMove = new TheKingMove(baseMove, this.getPosition());
        super.setPossibleMoves(kingMove.addMovablePosition(new HashSet<>(), chessMaps));
        return this.getPossibleMoves();
    }

}