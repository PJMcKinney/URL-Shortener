package urlShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import urlShortener.model.urlModel;

import java.util.UUID;

@Repository
public interface urlRepository extends JpaRepository<urlModel, UUID> {
}
