package micro.service.chessservice.service.impl;

import micro.service.chessservice.entity.MatchHistory;
import micro.service.chessservice.repository.MatchHistoryRepository;
import micro.service.chessservice.service.MatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchHistoryServiceImpl implements MatchHistoryService {

    @Autowired
    MatchHistoryRepository matchHistoryRepository;
    @Override
    public void saveMatchHistory(MatchHistory matchHistory) {

    }
    @Override
    public MatchHistory getMatchHistoryById(String matchId) {
        return null;
    }

    @Override
    public List<MatchHistory> getAllMatchHistories() {
        return matchHistoryRepository.findAll();
    }

    @Override
    public void deleteMatchHistoryById(String matchId) {

    }
}
