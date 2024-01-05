package micro.service.gameservice.entity;

import jakarta.persistence.*;
import lombok.*;
import micro.service.gameservice.entity.base.AdditionalInfo;

@Entity
@Table(name = "game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game extends AdditionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
