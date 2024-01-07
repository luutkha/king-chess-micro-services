package micro.service.chessservice.entity.external;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
