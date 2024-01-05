package micro.service.gameservice.entity.base;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class AdditionalInfo {
    @CreatedDate
    private Date createAt;
    @LastModifiedDate
    private Date updatedAt;

}
