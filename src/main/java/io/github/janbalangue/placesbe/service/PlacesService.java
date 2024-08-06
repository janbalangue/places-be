package io.github.janbalangue.placesbe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.janbalangue.placesbe.model.Place;
import org.pmw.tinylog.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlacesService {

    private final String placesApiKey;
    private String placesUrl;
    private final ObjectMapper objectMapper;

    public PlacesService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        Dotenv dotenv = Dotenv.load();
        placesApiKey = dotenv.get("PLACES_API_KEY");
        placesUrl = dotenv.get("PLACES_API_URl");
    }

    public Map<String, Object> getPlaces(String query) throws ClassCastException {
//        String[] placesArray = placesRequest.split(",");
//        String cleanPlacesRequest = String.join(" ", placesArray);
        Logger.info("Requesting places: {}", query);
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("textQuery", query);
        RestClient restClient = RestClient.builder().defaultHeaders(httpHeaders -> {
            httpHeaders.add("Content-Type", "application/json");
            httpHeaders.add("X-Goog-Api-Key", placesApiKey);
            httpHeaders.add("X-Goog-FieldMask", "places.displayName,places.formattedAddress,places.priceLevel,places.websiteUri");
        }).build();
        URI uri = URI.create("https://places.googleapis.com/v1/places:searchText");
        JsonNode response = restClient.post()
                .uri(uri)
                .body(rootNode)
                .retrieve()
                .body(JsonNode.class);
        Logger.debug("Response: {}", response);
        return objectMapper.convertValue(response, new TypeReference<>() {
        });
    }
}

