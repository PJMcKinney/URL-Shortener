package urlShortener.service;

import urlShortener.dto.urlModelDTO;
import urlShortener.exceptions.IdNotFoundException;
import urlShortener.model.urlModel;

import java.util.List;
import java.util.UUID;

public interface IurlService {

    UUID saveShortURL(urlModelDTO urlModelDTO);

    String getLongURL(String shortURL);

    String findShortURL(String longURL);

    List<urlModel> returnAllEntries();

    urlModelDTO updateURL(UUID id, urlModelDTO urlModelDTO) throws IdNotFoundException;

    void deleteEntry(UUID id) throws IdNotFoundException;

    urlModelDTO getEntryById(UUID id) throws IdNotFoundException;
}
