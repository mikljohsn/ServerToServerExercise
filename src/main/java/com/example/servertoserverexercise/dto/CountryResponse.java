package com.example.servertoserverexercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class CountryResponse {
    private int count;
    private String name;
    private List country = new ArrayList<>();
    private String country_id;
    private double probability;

    @Override
    public String toString() {
        return "CountryResponse{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", country_id='" + country_id + '\'' +
                ", probability=" + probability +
                '}';
    }
}

