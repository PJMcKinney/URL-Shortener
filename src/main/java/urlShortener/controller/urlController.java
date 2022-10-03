package urlShortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import urlShortener.Response;
import urlShortener.model.urlModel;
import urlShortener.service.urlService;

import java.util.UUID;

@RestController
@RequestMapping(path = "/url")
public class urlController {

    @Autowired
    urlService urlService;

    @PostMapping()
    public ResponseEntity<Response> saveURLRelationship(@RequestBody urlModel urlModel) {
        try {

            urlService.saveShortURL(urlModel);

            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("id", urlModel.getLongURL());

            return new ResponseEntity<>(
                    Response.builder()
                            .status(String.valueOf(HttpStatus.CREATED))
                            .response(urlModel).build(), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    Response.builder()
                            .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                            .response(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
