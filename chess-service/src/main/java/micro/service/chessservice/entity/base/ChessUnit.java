package micro.service.chessservice.entity.base;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.Chess;
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
        List<Square> blackChessMaps = chessMaps.stream().filter(e -> e.getSide().equals(SideConstant.BLACK)).map(Chess::getPosition).toList();
        List<Square> whiteChessMaps = chessMaps.stream().filter(e -> e.getSide().equals(SideConstant.WHITE)).map(Chess::getPosition).toList();
        Set<Square> squares;
        squares = switch (chess.getType()) {
            case ROOK -> {
                Chess rook = new Rook(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
                yield getPossibleMoves(chessMaps, chess, blackChessMaps, whiteChessMaps, rook);
            }
            case KNIGHT -> {
                Chess knight = new Knight(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
                yield getPossibleMoves(chessMaps, chess, blackChessMaps, whiteChessMaps, knight);
            }
            case PAWN -> {
                Chess pawn = new Pawn(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
                yield getPossibleMoves(chessMaps, chess, blackChessMaps, whiteChessMaps, pawn);
            }
            case BISHOP -> {
                Chess bishop = new Bishop(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
                yield getPossibleMoves(chessMaps, chess, blackChessMaps, whiteChessMaps, bishop);
            }
            case QUEEN -> {
                Chess queen = new Queen(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
                yield getPossibleMoves(chessMaps, chess, blackChessMaps, whiteChessMaps, queen);
            }
            case KING -> {
                Chess king = new King(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
                yield getPossibleMoves(chessMaps, chess, blackChessMaps, whiteChessMaps, king);
            }
        };
        return squares;
    }

    private static Set<Square> getPossibleMoves(List<Chess> chessMaps, Chess chess, List<Square> blackChessMaps, List<Square> whiteChessMaps, Chess newChessInstance) {
        Set<Square> possibleMoves = newChessInstance.generatePossibleMoves(chess.getPossibleMoves(), chessMaps);
        possibleMoves.remove(chess.getPosition());
        if (chess.getSide().equals(SideConstant.WHITE)) {
            whiteChessMaps.forEach(possibleMoves::remove);
        } else {
            blackChessMaps.forEach(possibleMoves::remove);
        }
        return possibleMoves;
    }
}
