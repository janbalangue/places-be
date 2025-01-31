package io.github.janbalangue.placesbe.controller;

import io.github.janbalangue.placesbe.service.PlacesService;
import org.pmw.tinylog.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/places")
public class PlacesController {
    private final PlacesService placesService;
    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping(value = "/{textQuery}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getPlaces(@PathVariable("textQuery") String textQuery) {
        Logger.info("Processing text query: {}", textQuery);
        String url = "https://places.googleapis.com/v1/places:searchText";
        Map<String, Object> places = placesService.getPlaces(textQuery, url);
        Logger.debug("Places returned: {}", places);
        Logger.info("Text query succeeded");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(places, headers, HttpStatus.OK);
    }
}
