package com.example.servertoserverexercise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Object country;

    public FullResponse(GenderResponse gender, AgeResponse age, CountryResponse nationality) {
        this.name = gender.getName();
        this.gender = gender.getGender();
        this.genderProbability = gender.getProbability();
        this.age = age.getAge();
        this.ageCount = age.getCount();
        // Assuming nationality.getCountry() returns a list
        // Assuming nationality.getCountry() returns a list
        if (!nationality.getCountry().isEmpty()) {
            CountryResponse firstCountry = (CountryResponse) nationality.getCountry().get(0);
            this.country = firstCountry.getCountry_id();
            this.countryProbability = firstCountry.getProbability();
        } else {
            this.country = null;
            this.countryProbability = 0.0; // Set a default value or handle this case as needed
        }
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
                ", country='" + country + '\'' +
                '}';
    }
}
