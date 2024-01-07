package micro.service.chessservice.repository;

import micro.service.chessservice.entity.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Long> {

   List< MatchHistory> findByGameId(Integer matchId);
}
