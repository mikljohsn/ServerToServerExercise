package com.example.servertoserverexercise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FullResponse {
    private int age;
    private int ageCount;
    private String name;
    private String gender;
    private double genderProbability;
    private double countryProbability;
    private List country = new ArrayList<>();

    public FullResponse(GenderResponse gender, AgeResponse age, CountryResponse nationality) {
        this.name = gender.getName();
        this.gender = gender.getGender();
        this.genderProbability = gender.getProbability();
        this.age = age.getAge();
        this.ageCount = age.getCount();
        this.country = nationality.getCountry();
        this.countryProbability = nationality.getCountryProbability();
    }

    @Override
    public String toString() {
        return "FullResponse{" +
                "age=" + age +
                ", ageCount=" + ageCount +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", genderProbability=" + genderProbability +
                ", countryProbability=" + countryProbability +
                ", country=" + country +
                '}';
    }
}
