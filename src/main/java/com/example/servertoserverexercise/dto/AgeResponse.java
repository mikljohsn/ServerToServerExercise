package com.example.servertoserverexercise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgeResponse {
    private int count;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "AgeResponse{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
