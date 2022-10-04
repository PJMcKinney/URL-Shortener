package urlShortener.service;

import urlShortener.dto.urlModelDTO;
import urlShortener.model.urlModel;

public interface IurlService {

    void saveShortURL(urlModelDTO urlModelDTO);

    String getLongURL(String shortURL);
}
