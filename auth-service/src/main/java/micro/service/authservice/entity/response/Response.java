package micro.service.authservice.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {
    private T data;
    private String message;
    private int statusCode;

//    public Response(T data, String message, int statusCode) {
//        this.data = data;
//        this.message = message;
//        this.statusCode = statusCode;
//    }
}
