package urlShortener.service;

import urlShortener.dto.urlModelDTO;
import urlShortener.model.urlModel;

import java.util.List;

public interface IurlService {

    void saveShortURL(urlModelDTO urlModelDTO);

    String getLongURL(String shortURL);

    List<urlModel> returnAllEntries();
}
