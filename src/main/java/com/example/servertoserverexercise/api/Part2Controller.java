package com.example.servertoserverexercise.api;

import com.example.servertoserverexercise.dto.AgeResponse;
import com.example.servertoserverexercise.dto.CountryResponse;
import com.example.servertoserverexercise.dto.FullResponse;
import com.example.servertoserverexercise.dto.GenderResponse;
import com.example.servertoserverexercise.service.NameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Part2Controller {
    @Autowired
    private NameInfoService nameInfoService;
    @GetMapping("api/name-info")
    public Mono<FullResponse> getNameInfo(@RequestParam String name) {
        Mono<AgeResponse> ageMono = nameInfoService.getAgeInfo(name);
        Mono<GenderResponse> genderMono = nameInfoService.getGenderInfo(name);
        Mono<CountryResponse> countryMono = nameInfoService.getCountryInfo(name);

        return Mono.zip(ageMono, genderMono, countryMono)
                .map(tuple -> {
                    AgeResponse ageResponse = tuple.getT1();
                    GenderResponse genderResponse = tuple.getT2();
                    CountryResponse countryResponse = tuple.getT3();

                    FullResponse res = new FullResponse(genderResponse, ageResponse, countryResponse);
                    res.setName(name);
                    res.setGender(genderResponse.getGender());
                    res.setGenderProbability(genderResponse.getProbability());
                    res.setAge(ageResponse.getAge());
                    res.setAgeCount(ageResponse.getCount());
                    res.setCountry(countryResponse.getCountry());
                    res.setCountryProbability(countryResponse.getCountryProbability());

                    return res;
                });
    }
}
