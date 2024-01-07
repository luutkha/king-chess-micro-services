package micro.service.chessservice.controller;

import micro.service.chessservice.config.WebClientConfig;
import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.ChessBoard;
import micro.service.chessservice.entity.ChessUnitMovable;
import micro.service.chessservice.entity.MatchHistory;
import micro.service.chessservice.entity.Square;
import micro.service.chessservice.entity.chess.Queen;
import micro.service.chessservice.entity.external.Game;
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
public class MatchController {

    public static final String GAME_SERVICE_URI = "lb://GAME-SERVICE//";
    @Autowired
    private MatchHistoryService matchHistoryService;
    @Autowired
    private WebClientConfig webClientConfig;

//    @GetMapping("/{id}")
//    public ResponseEntity<MatchHistory> getMatchById(@PathVariable Long id) {
//        MatchHistory match = matchHistoryService.;
//        if (match != null) {
//            return ResponseEntity.ok(match);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

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
        chessBoard.setGameId(resp.getId());
        chessBoard.createGameStartMovable();
        return ResponseEntity.status(HttpStatus.CREATED).body(chessBoard);
    }

    @PostMapping("/move")
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<MatchHistory> moveAChess(@RequestBody MatchHistory match) {
        MatchHistory createdMatch = matchHistoryService.moveAChess(match);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatch);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<MatchHistory> updateMatch(@PathVariable Long id, @RequestBody MatchHistory match) {
//        MatchHistory updatedMatch = matchService.updateMatch(id, match);
//        if (updatedMatch != null) {
//            return ResponseEntity.ok(updatedMatch);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
//        boolean deleted = matchService.deleteMatch(id);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}