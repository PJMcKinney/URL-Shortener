package urlShortener;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import netscape.javascript.JSObject;

@Setter
@Getter
@Builder
public class Response<T> {

    private T response;
    private String status;
}
