package com.example.servertoserverexercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CountryResponse {
    private int count;
    private String name;
    private List country = new ArrayList<>();
    private String country_id;
    private double countryProbability;

    public CountryResponse(int count, String name, List country, String country_id, double countryProbability) {
        this.count = count;
        this.name = name;
        this.country = country;
        this.country_id = country_id;
        this.countryProbability = countryProbability;
    }

    public CountryResponse(int count, String name, List country) {
        this.count = count;
        this.name = name;
        this.country = country;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CountryInfo {
        private String country_id;
        private double probability;

        public CountryInfo(String country_id, double probability) {
            this.country_id = country_id;
            this.probability = probability;
        }

        @Override
        public String toString() {
            return "CountryInfo{" +
                    "country_id='" + country_id + '\'' +
                    ", probability=" + probability +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CountryResponse{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", country_id='" + country_id + '\'' +
                ", countryProbability=" + countryProbability +
                '}';
    }
}

