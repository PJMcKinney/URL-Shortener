package urlShortener.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseDTO<T> {

    private T response;
    private String status;
}
