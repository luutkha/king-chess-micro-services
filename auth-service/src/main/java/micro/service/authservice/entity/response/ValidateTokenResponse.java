package micro.service.authservice.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ValidateTokenResponse {
    private boolean isValid;
}
