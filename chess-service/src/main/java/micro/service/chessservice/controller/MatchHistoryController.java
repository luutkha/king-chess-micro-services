package micro.service.chessservice.controller;

import lombok.extern.log4j.Log4j2;
import micro.service.chessservice.entity.MatchHistory;
import micro.service.chessservice.service.MatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@Log4j2
//@RequiredArgsConstructor
public class MatchHistoryController {

    @Autowired
    private MatchHistoryService matchHistoryService;

    @PostMapping
    public ResponseEntity<String> saveMatchHistory(@RequestBody MatchHistory matchHistory) {
        // Logic to save the match history
        matchHistoryService.saveMatchHistory(matchHistory);
        return ResponseEntity.ok("Match history saved successfully");
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchHistory> getMatchHistoryById(@PathVariable String matchId) {
        // Logic to retrieve the match history by ID
        MatchHistory matchHistory = matchHistoryService.getMatchHistoryById(matchId);
        if (matchHistory != null) {
            return ResponseEntity.ok(matchHistory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MatchHistory>> getAllMatchHistories() {
        // Logic to retrieve all match histories
        log.info("CALL SUCCESS");
        List<MatchHistory> matchHistories = matchHistoryService.getAllMatchHistories();
        return ResponseEntity.ok(matchHistories);
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity<String> deleteMatchHistoryById(@PathVariable String matchId) {
        // Logic to delete the match history by ID
        matchHistoryService.deleteMatchHistoryById(matchId);
        return ResponseEntity.ok("Match history deleted successfully");
    }
}