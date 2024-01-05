package micro.service.chessservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@Entity
//@Table(name = "square")
public class Square {
    private int x;
    private int y;
//    @Id
//    @GeneratedValue
//    private Long id;


}
