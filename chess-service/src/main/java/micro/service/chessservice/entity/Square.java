package micro.service.chessservice.entity;

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
