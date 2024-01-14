package micro.service.chessservice.controller;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.config.WebClientConfig;
import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.*;
import micro.service.chessservice.entity.chess.Queen;
import micro.service.chessservice.entity.external.Game;
import micro.service.chessservice.entity.request.MoveAChessRequest;
import micro.service.chessservice.entity.response.MoveAChessResponse;
import micro.service.chessservice.service.MatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/matches")
@Log4j2
public class MatchController {

    public static final String GAME_SERVICE_URI = "lb://GAME-SERVICE//";
    @Autowired
    private MatchHistoryService matchHistoryService;
    @Autowired
    private WebClientConfig webClientConfig;

    private static List<ChessUnitMovable> createChessUnitMovables() {
        ChessUnitMovable chessUnitMovable = ChessUnitMovable.builder().chessUnitConstant(ChessUnitConstant.KING).movablePositions(new HashSet<>()).currentPosition(new Square(1, 1)).build();
        List<ChessUnitMovable> chessUnitMovables = new ArrayList<>();
        chessUnitMovables.add(chessUnitMovable);
        return chessUnitMovables;
    }

    @GetMapping("/test")
    public ResponseEntity<Queen> test() {
        Queen queen = new Queen(SideConstant.WHITE, new Square(1, 1), new HashSet<>());
        queen.setPossibleMoves(new HashSet<>());
        return ResponseEntity.ok(queen);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ChessBoard> createMatch() {
        Game resp = webClientConfig.webClientBuilder().build().post().uri(GAME_SERVICE_URI).retrieve().bodyToMono(Game.class).block();

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setCount(0);
        if (resp != null) {
            chessBoard.setGameId(resp.getId());
        }
        chessBoard.createGameStartMovable();
        return ResponseEntity.status(HttpStatus.CREATED).body(chessBoard);
    }

    @PostMapping("/move")
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<MoveAChessResponse> moveAChess(@RequestBody MoveAChessRequest match) {
        MatchHistory matchHistory = matchHistoryService.moveAChess(match);
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setChessMaps(match.getChessBoard().getChessMaps());
        Chess chess = Chess.builder()
                .type(match.getType())
                .side(match.getSide())
                .position(new Square(match.getCurrentPositionX(), match.getCurrentPositionY()))
                .build();
        chessBoard.processMoveAChess(chess, new Square(match.getNewPositionX(), match.getNewPositionY()));
        MoveAChessResponse moveAChessResponse = MoveAChessResponse.builder()
                .chessBoard(chessBoard)
                .matchHistory(matchHistory)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(moveAChessResponse);
    }
}