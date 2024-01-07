package micro.service.chessservice.entity.external;

import jakarta.persistence.*;
import lombok.*;

//@Entity
//@Table(name = "game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@EntityListeners(AuditingEntityListener.class)
public class Game extends AdditionalInfo {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
