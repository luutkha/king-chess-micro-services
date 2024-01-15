package micro.service.chessservice.entity;


import lombok.*;
import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.constant.ChessBoardConstant;
import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.base.ChessUnit;
import micro.service.chessservice.entity.base.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class ChessBoard {
    private Integer gameId;
//    private Integer id;
    private Integer count;
    private List<Chess> chessMaps;

    private static void addOppositeChess(Chess chess, List<Chess> chessList) {
        Square oppositeSquare = Position.getOppositeSquare(chess.getPosition().getX(), chess.getPosition().getY());
        SideConstant oppositeSide = ChessUnit.getOpposite(chess.getSide());
        Chess oppositeChess = Chess.builder().position(oppositeSquare).type(chess.getType()).side(oppositeSide).build();
        chessList.add(oppositeChess);
    }

    private static void addOtherSideChess(Chess chess, List<Chess> chessList) {
        Square otherSideSquare = Position.getOtherSideSquare(chess.getPosition().getX(), chess.getPosition().getY());
        Chess otherSideChess = Chess.builder().position(otherSideSquare).type(isQueenUnit(chess) ? ChessUnitConstant.KING : chess.getType()).side(chess.getSide()).build();
        chessList.add(otherSideChess);
    }

    private static boolean isPawnUnit(Chess chess) {
        return chess.getType().equals(ChessUnitConstant.PAWN);
    }

    private static boolean isQueenUnit(Chess chess) {
        return chess.getType().equals(ChessUnitConstant.QUEEN);
    }

    private static void generateBasePositionOfChess(List<Chess> chessList) {
        for (int i = ChessBoardConstant.MIN_X; i <= ChessBoardConstant.MAX_X; i++) {
            chessList.add(Chess.builder().side(SideConstant.WHITE).type(ChessUnitConstant.PAWN).position(new Square(i, ChessBoardConstant.MIN_Y + 1)).build());
        }
        chessList.add(Chess.builder().side(SideConstant.WHITE).type(ChessUnitConstant.ROOK).position(new Square(1, ChessBoardConstant.MIN_Y)).build());
        chessList.add(Chess.builder().side(SideConstant.WHITE).type(ChessUnitConstant.KNIGHT).position(new Square(2, ChessBoardConstant.MIN_Y)).build());
        chessList.add(Chess.builder().side(SideConstant.WHITE).type(ChessUnitConstant.BISHOP).position(new Square(3, ChessBoardConstant.MIN_Y)).build());
        chessList.add(Chess.builder().side(SideConstant.WHITE).type(ChessUnitConstant.QUEEN).position(new Square(4, ChessBoardConstant.MIN_Y)).build());
    }

    public void createGameStartMovable() {
        List<Chess> chessList = new ArrayList<>();

        generateBasePositionOfChess(chessList);
        List<Chess> chessListTemp = new ArrayList<>(chessList);
        if (chessList.isEmpty()) {
            log.error("chessList EMPTY");
        } else {
            chessListTemp.forEach(chess -> {
                if (!isPawnUnit(chess)) {
                    addOtherSideChess(chess, chessList);
                }
            });

            chessListTemp.clear();
            chessListTemp.addAll(chessList);
            chessListTemp.forEach(chess -> addOppositeChess(chess, chessList));
        }

        log.info("{} CREATED ALL POSITION OF CHESS UNIT ON CHESSBOARD", chessList.size());
        this.chessMaps = chessList;
        chessList.forEach(chess -> {
            chess.setPossibleMoves(ChessUnit.generateMovablePositionOfChessUnit(chessMaps, chess));
        });
        this.chessMaps = chessList;
    }


    public void processMoveAChess(Chess chess, Square newPosition) {
        List<Chess> chessOfEnemyWillBeDeleted = new ArrayList<>();

        updateChessToNewPositionAndKillEnemy(chess, newPosition, chessOfEnemyWillBeDeleted);

        this.chessMaps.removeAll(chessOfEnemyWillBeDeleted);
        this.chessMaps.stream().map(c -> {
            if (!c.getSide().equals(ChessUnit.getOpposite(chess.getSide()))) {
                c.setPossibleMoves(new HashSet<>());
            }
            else {
                Set<Square> squares = ChessUnit.generateMovablePositionOfChessUnit(chessMaps, c);
                c.setPossibleMoves(squares);
            }
            return c;
        });

    }


    private void updateChessToNewPositionAndKillEnemy(Chess chess, Square newPosition, List<Chess> chessOfEnemyWillBeDeleted) {
        this.getChessMaps().forEach(e -> {
            if (e.getSide().equals(chess.getSide()) && e.getType().equals(chess.getType()) && e.getPosition().equals(chess.getPosition())) {
                e.setPosition(newPosition);
                chessOfEnemyWillBeDeleted.addAll(this.getChessMaps().stream().filter(ch -> ch.getPosition().equals(newPosition)).toList());
            }
        });
    }

}
