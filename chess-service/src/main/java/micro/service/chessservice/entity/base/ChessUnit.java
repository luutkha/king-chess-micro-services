package micro.service.chessservice.entity.base;

import micro.service.chessservice.constant.SideConstant;

public final class ChessUnit {

    public static SideConstant getOpposite(SideConstant side) {
        if (side.equals(SideConstant.BLACK)) return SideConstant.WHITE;
        return SideConstant.BLACK;
    }

    ;
}
