package micro.service.chessservice.entity.base;

import micro.service.chessservice.constant.ChessBoardConstant;
import micro.service.chessservice.entity.Square;

public final class Position {

    public static Square getOppositeSquare(Integer x, Integer y){
        int oppositeX = Math.abs(ChessBoardConstant.MAX_X - x + 1);
        int oppositeY = Math.abs(ChessBoardConstant.MAX_Y - y + 1);
        return new Square(oppositeX, oppositeY);
    }
    public static  Square getOtherSideSquare(Integer x, Integer y){
        int oppositeX = Math.abs(ChessBoardConstant.MAX_X - x + 1);
        int oppositeY = Math.abs(y);
        return new Square(oppositeX, oppositeY);
    }
}
