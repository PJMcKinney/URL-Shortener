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
import java.util.UUID;

@RestController
@CrossOrigin()
@RequestMapping(path = "/url")
public class urlController {

    @Autowired
    urlService urlService;

    @PostMapping()
    public ResponseEntity<ResponseDTO> saveURLRelationship(@RequestBody urlModelDTO urlModelDTO ) {
        try {

            UUID id = urlService.saveShortURL(urlModelDTO);

            urlModelDTO returnedUrlModelDTO = urlService.getEntryById(id);

            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("shortURL", urlModelDTO.getShortURL());

            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.CREATED))
                            .response(returnedUrlModelDTO).build(), headers, HttpStatus.CREATED);
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

    @GetMapping("/allEntries")
    public ResponseEntity<ResponseDTO> returnAllEntries() {
        try {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.OK))
                            .response(urlService.returnAllEntries()).build(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                            .response(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/updateURL/{id}")
    public ResponseEntity<ResponseDTO> updateEntry(@PathVariable("id") UUID id, @RequestBody urlModelDTO urlModelDTO) {
        try {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.OK))
                            .response(urlService.updateURL(id, urlModelDTO)).build(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                            .response(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/deleteURL/{id}")
    public ResponseEntity<ResponseDTO> deleteEntry(@PathVariable("id") UUID id) {
        try {
            urlModelDTO urlModelDTO =urlService.getEntryById(id);
            urlService.deleteEntry(id);

            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.OK))
                            .response("Deleted entry with ID: " + urlModelDTO.getId()).build(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                            .response(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/returnEntryById/{id}")
    public ResponseEntity<ResponseDTO> returnEntryById(@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .status(String.valueOf(HttpStatus.FOUND))
                            .response(urlService.getEntryById(id)).build(), HttpStatus.FOUND);

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
