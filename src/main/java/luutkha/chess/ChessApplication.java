package luutkha.chess;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChessApplication.class, args);
//        XMove move = new XMove(new Square(3, 3));
//        move.getMovablePosition().forEach(e -> System.out.println(e.toString()));
    }

}
