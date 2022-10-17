package urlShortener.service;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urlShortener.dto.urlModelDTO;
import urlShortener.exceptions.IdNotFoundException;
import urlShortener.mapper.urlMapper;
import urlShortener.model.urlModel;
import urlShortener.repository.urlRepository;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class urlService implements IurlService{
    @Autowired
    urlRepository repository;

    public void saveShortURL(urlModelDTO urlModelDTO) {

        urlModel urlModel = urlMapper.toUrlModel(urlModelDTO);

        String hashValue = Hashing
                .sha256()
                .hashString(urlModel.getLongURL(), StandardCharsets.UTF_8)
                .toString().substring(0,9);
        urlModelDTO.setShortURL(hashValue);

        if(isNull(findShortURL(urlModelDTO.getLongURL()))) {
            urlModel.setShortURL(hashValue);
            repository.save(urlModel);
        }
    }

    public String getLongURL(String shortURL) {
        return repository.findLongURL(shortURL);
        }

    @Override
    public String findShortURL(String longURL) {
        return repository.findShortURL(longURL);
    }

    public List<urlModel> returnAllEntries() {
        return repository.findAll();
    }

    public urlModelDTO updateURL(UUID id, urlModelDTO urlModelDTO) throws IdNotFoundException {
        if (!repository.existsById(id)) {
            throw new IdNotFoundException ("Cannot update entry - Invalid ID");
        }

        urlModel urlModel = urlMapper.toUrlModel(urlModelDTO);
        urlModel.setId(id);

        return urlMapper.toUrlModelDTO(repository.save(urlModel));
    }

    public void deleteEntry(UUID id) throws IdNotFoundException {
        if (!repository.existsById(id)) {
            throw new IdNotFoundException("Cannot delete entry - Invalid ID");
        }
        repository.deleteById(id);
    }

    public urlModelDTO getEntryById(UUID id) throws IdNotFoundException {
        urlModel urlModel = repository.findById(id).orElseThrow(() -> new IdNotFoundException("Invalid ID"));
        return urlMapper.toUrlModelDTO(urlModel);
    }
}

