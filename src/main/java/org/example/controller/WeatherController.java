package org.example.controller;

import org.example.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city/*,@RequestParam String country_Code*/){
        System.out.println("HEllo Controller");
        String res = weatherService.getWeatherOfCity(city/*,country_Code*/);

        return res;
    }

}
