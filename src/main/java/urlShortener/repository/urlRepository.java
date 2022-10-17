package urlShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import urlShortener.model.urlModel;

import java.util.UUID;

@Repository
public interface urlRepository extends JpaRepository<urlModel, UUID> {

    @Query(value = "SELECT longURL from urls where shortURL = :shortURL", nativeQuery = true)
    String findLongURL(@Param("shortURL") String shortURL);

    @Query(value = "SELECT shortURL from urls where longURL = :longURL", nativeQuery = true)
    String findShortURL(@Param("longURL") String longURL);

}