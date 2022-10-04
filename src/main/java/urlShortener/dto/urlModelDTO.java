package urlShortener.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class urlModelDTO {

    private UUID id;
    private String longURL;
    private String shortURL;
    private LocalDateTime createdAt;

    public urlModelDTO() {
    }
}
