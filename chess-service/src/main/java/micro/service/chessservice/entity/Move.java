package micro.service.chessservice.entity;


import java.util.Set;

public interface Move {

    boolean moveTo();
   Set<Square> addMovablePosition(Set<Square> movablePosition);
}
