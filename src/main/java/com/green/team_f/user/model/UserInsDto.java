package com.green.team_f.user.model;

import lombok.Data;

@Data
public class UserInsDto {
    private String uid;
    private String upw;
    private String name;
    private int age;
    private int height;
    private int weight;
    private char gender;
    private String goal;
}
