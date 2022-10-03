package urlShortener.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "URLs")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class urlModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "longURL", updatable = true, nullable = false)
    private String longURL;

    @Column(name = "shortURL")
    private String shortURL;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public urlModel() {
    }
}
