package micro.service.chessservice.entity.base;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.ChessBoard;
import micro.service.chessservice.entity.MatchHistory;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.chess.*;

import java.util.List;
import java.util.Set;

@Log4j2
public final class ChessUnit {

    public static SideConstant getOpposite(SideConstant side) {
        if (side.equals(SideConstant.BLACK)) return SideConstant.WHITE;
        return SideConstant.BLACK;
    }

    public static Set<Square> generateMovablePositionOfChessUnit(List<Chess> chessMaps, Chess chess) {

        Chess chessMapped = switch (chess.getType()) {
            case ROOK -> new Rook(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
            case KNIGHT -> new Knight(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
            case PAWN -> new Pawn(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
            case BISHOP -> new Bishop(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
            case QUEEN -> new Queen(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
            case KING -> new King(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
        };

        return getPossibleMoves(chessMaps, chess, chessMapped);

    }

    private static Set<Square> getPossibleMoves(List<Chess> chessMaps, Chess chess, Chess newChessInstance) {
        Set<Square> possibleMoves = newChessInstance.generatePossibleMoves(chess.getPossibleMoves(), chessMaps);
        removeMovablePositionsThatContainAllyUnit(chessMaps, chess, possibleMoves);
        return possibleMoves;
    }

    private static void removeMovablePositionsThatContainAllyUnit(List<Chess> chessMaps, Chess chess, Set<Square> possibleMoves) {
        if (chess.getSide().equals(SideConstant.WHITE)) {
            List<Square> whiteChessMaps = chessMaps.stream().filter(e -> e.getSide().equals(SideConstant.WHITE)).map(Chess::getPosition).toList();
            whiteChessMaps.forEach(possibleMoves::remove);
        } else {
            List<Square> blackChessMaps = chessMaps.stream().filter(e -> e.getSide().equals(SideConstant.BLACK)).map(Chess::getPosition).toList();
            blackChessMaps.forEach(possibleMoves::remove);
        }
    }

    public static boolean isQualifiedMove(MatchHistory history, ChessBoard chessBoard) {
        Chess chess = Chess.builder()
                .side(history.getSide())
                .type(history.getType())
                .position(new Square(history.getCurrentPositionX(), history.getCurrentPositionY()))
                .build();
        List<Chess> chessUnitQualified = chessBoard.getChessMaps().stream().filter(e -> e.getSide().equals(chess.getSide()) && e.getType().equals(chess.getType()) && e.getPosition().equals(chess.getPosition())).toList();

        if (!chessUnitQualified.isEmpty()) {
            return chessUnitQualified.get(0).getPossibleMoves().contains(new Square(history.getNewPositionX(), history.getNewPositionY()));
        }
        return false;
    }
}
