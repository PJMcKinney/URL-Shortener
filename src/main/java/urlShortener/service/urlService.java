package urlShortener.service;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import urlShortener.model.urlModel;
import urlShortener.repository.urlRepository;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class urlService implements IurlService{
    @Autowired
    urlRepository repository;

    public void saveShortURL( urlModel urlModel) {

        urlModel.setShortURL(Hashing
                .sha256()
                .hashString(urlModel.getLongURL(), StandardCharsets.UTF_8)
                .toString());

        repository.save(urlModel);
    }
}
