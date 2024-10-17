package org.example.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class WeatherService {
    private final WebClient webclient;
    private final ObjectMapper objectMapper;
    private final String api_key="5d7fda2a7b3818dffec6791ca1b92ce3";



    public WeatherService(WebClient.Builder webClientBuilder){
        this.webclient= webClientBuilder.baseUrl("https://api.openweathermap.org/data/2.5").build();
        this.objectMapper= new ObjectMapper();
    }


    public String getWeatherOfCity(String city/*,String countryCode*/){

        Mono<String> res=  this.webclient.get()
                .uri(uriBuilder -> uriBuilder.path("/weather")
                        .queryParam("q",city)
                        .queryParam("appid",api_key)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
        String weatherData= res.block();
        System.out.println("City: "+city+"::\n "+weatherData);

        try{
            Object json= objectMapper.readValue(weatherData,Object.class);
            String prettyJsonRes= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            System.out.println("Response in Json\n: "+prettyJsonRes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return weatherData;

    }
}
