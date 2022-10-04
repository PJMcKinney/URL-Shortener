package urlShortener.mapper;

import urlShortener.dto.urlModelDTO;
import urlShortener.model.urlModel;

public class urlMapper {

    public static urlModel toUrlModel(urlModelDTO urlModelDTO) {
        return urlModel.builder()
                .id(urlModelDTO.getId())
                .longURL(urlModelDTO.getLongURL())
                .shortURL(urlModelDTO.getShortURL())
                .createdAt(urlModelDTO.getCreatedAt())
                .build();
    }

    public static urlModelDTO toUrlModelDTO(urlModel urlModel) {
        return urlModelDTO.builder()
                .id(urlModel.getId())
                .longURL(urlModel.getLongURL())
                .shortURL(urlModel.getShortURL())
                .createdAt(urlModel.getCreatedAt())
                .build();
    }
}
