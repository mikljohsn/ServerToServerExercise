package com.example.servertoserverexercise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenderResponse {
    String gender;
    String name;
    int count;
    double probability;

    @Override
    public String toString() {
        return "GenderResponse{" +
                "gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", probability=" + probability +
                '}';
    }
}
