package urlShortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import urlShortener.dto.ResponseDTO;
import urlShortener.dto.urlModelDTO;
import urlShortener.mapper.urlMapper;
import urlShortener.model.urlModel;
import urlShortener.service.urlService;

import java.net.URI;

@RestController
@RequestMapping(path = "/url")
public class urlController {

    @Autowired
    urlService urlService;

    @PostMapping()
    public ResponseEntity<ResponseDTO> saveURLRelationship(@RequestBody urlModelDTO urlModelDTO ) {
        try {

            urlService.saveShortURL(urlModelDTO);

            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("id", urlModelDTO.getLongURL());

            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.CREATED))
                            .response(urlModelDTO).build(), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                            .response(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/{shortURL}")
    public ResponseEntity<ResponseDTO> redirectToLongURL(@PathVariable String shortURL) {
        try {

            URI redirect = new URI(urlService.getLongURL(shortURL));
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirect);

            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.SEE_OTHER))
                            .response("Redirecting...").build(), httpHeaders, HttpStatus.SEE_OTHER);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                            .response(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
