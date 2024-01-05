package micro.service.chessservice.service;

import micro.service.chessservice.entity.MatchHistory;

import java.util.List;

//@Service
public interface MatchHistoryService {
    void saveMatchHistory(MatchHistory matchHistory);

    MatchHistory getMatchHistoryById(String matchId);

    List<MatchHistory> getAllMatchHistories();

    void deleteMatchHistoryById(String matchId);
}
