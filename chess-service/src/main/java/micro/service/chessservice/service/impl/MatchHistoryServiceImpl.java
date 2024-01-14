package micro.service.chessservice.service.impl;

import micro.service.chessservice.constant.ChessUnitConstant;
import micro.service.chessservice.constant.SideConstant;
import micro.service.chessservice.entity.MatchHistory;
import micro.service.chessservice.entity.base.ChessUnit;
import micro.service.chessservice.entity.request.MoveAChessRequest;
import micro.service.chessservice.repository.MatchHistoryRepository;
import micro.service.chessservice.service.MatchHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchHistoryServiceImpl implements MatchHistoryService {

    @Autowired
    MatchHistoryRepository matchHistoryRepository;

    @Override
    public void saveMatchHistory(MatchHistory matchHistory) {

    }

    @Override
    public List<MatchHistory> getMatchHistoryById(Integer matchId) {
        return matchHistoryRepository.findByGameId(matchId);
    }

    @Override
    public List<MatchHistory> getAllMatchHistories() {
        return matchHistoryRepository.findAll();
    }

    @Override
    public void deleteMatchHistoryById(String matchId) {

    }

    @Override
    public MatchHistory createMatch() {
        MatchHistory matchHistory = MatchHistory.builder()
                .step(0)
                .side(SideConstant.WHITE)
                .type(ChessUnitConstant.KING)
                .newPositionY(1)
                .currentPositionY(1)
                .newPositionX(1)
                .currentPositionX(1)
                .build();
        return matchHistoryRepository.save(matchHistory);
    }

    @Override
    public MatchHistory moveAChess(MoveAChessRequest match) {
        MatchHistory matchHistory = convertDtoToEntity(match);
        if (ChessUnit.isQualifiedMove(matchHistory, match.getChessBoard())) {
            return matchHistoryRepository.save(matchHistory);
        }
        throw new RuntimeException();
    }

    private static MatchHistory convertDtoToEntity(MoveAChessRequest match) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(match, MatchHistory.class);
    }

}
