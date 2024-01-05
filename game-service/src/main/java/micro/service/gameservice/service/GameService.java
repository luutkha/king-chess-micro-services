package micro.service.gameservice.service;

import micro.service.gameservice.entity.Game;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();

    Game getGameById(Long id);

    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    void deleteGame(Long id);
}