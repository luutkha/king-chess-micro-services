package micro.service.chessservice.service;

import micro.service.chessservice.entity.MatchHistory;
import micro.service.chessservice.entity.exception.WrongMoveException;
import micro.service.chessservice.entity.request.MoveAChessRequest;

import java.util.List;

//@Service
public interface MatchHistoryService {
    void saveMatchHistory(MatchHistory matchHistory);

    List<MatchHistory> getMatchHistoryById(Integer matchId);

    List<MatchHistory> getAllMatchHistories();

    void deleteMatchHistoryById(String matchId);

    MatchHistory createMatch();

    MatchHistory moveAChess(MoveAChessRequest match) throws WrongMoveException;
}
