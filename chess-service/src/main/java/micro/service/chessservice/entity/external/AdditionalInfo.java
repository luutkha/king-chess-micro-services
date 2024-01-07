package micro.service.chessservice.entity.external;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@Data
//@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AdditionalInfo {

    //    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    //    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
