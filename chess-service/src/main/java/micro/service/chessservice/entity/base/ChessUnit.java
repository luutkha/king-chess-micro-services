package micro.service.chessservice.entity.base;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.Chess;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.chess.Queen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
public final class ChessUnit {

    public static SideConstant getOpposite(SideConstant side) {
        if (side.equals(SideConstant.BLACK)) return SideConstant.WHITE;
        return SideConstant.BLACK;
    }

    public static Set<Square> generateMovablePositionOfChessUnit(List<Chess> chessMaps, Chess chess) {
//        log.info("GENERATE CHESS MOVABLE POSITION");
        Set<Square> squares = new HashSet<>();
        squares = switch (chess.getType()) {
//            case ROOK -> {
//            }
//            case KNIGHT -> {
//            }
//            case KING, PAWN -> {
//
//            }
//
//            case BISHOP -> {
//            }
            case QUEEN -> {
                Queen queen = new Queen(chess.getSide(), chess.getPosition(), chess.getPossibleMoves());
                Set<Square> possibleMoves = queen.generatePossibleMoves(chess.getPossibleMoves(), chessMaps);
                possibleMoves.remove(chess.getPosition());
                yield possibleMoves;

            }
            case BISHOP, KING, KNIGHT, PAWN, ROOK -> new HashSet<>();
        };
        return squares;
    }
}
