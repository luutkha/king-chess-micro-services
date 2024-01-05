package micro.service.gameservice.service.impl;

import micro.service.gameservice.entity.Game;
import micro.service.gameservice.repository.GameRepository;
import micro.service.gameservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private  GameRepository gameRepository;
    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGameById(Long id) {
        return null;
    }

    @Override
    public Game createGame(Game game) {
        return null;
    }

    @Override
    public Game updateGame(Long id, Game game) {
        return null;
    }

    @Override
    public void deleteGame(Long id) {

    }
}
