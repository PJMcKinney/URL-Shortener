package urlShortener.service;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urlShortener.dto.urlModelDTO;
import urlShortener.mapper.urlMapper;
import urlShortener.model.urlModel;
import urlShortener.repository.urlRepository;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class urlService implements IurlService{
    @Autowired
    urlRepository repository;

    public void saveShortURL( urlModelDTO urlModelDTO) {

        urlModel urlModel = urlMapper.toUrlModel(urlModelDTO);

        urlModel.setShortURL(Hashing
                .sha256()
                .hashString(urlModel.getLongURL(), StandardCharsets.UTF_8)
                .toString().substring(0,9));

        repository.save(urlModel);
    }

    public String getLongURL(String shortURL) {
        return repository.findLongURL(shortURL);
        }
}

