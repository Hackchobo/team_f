package com.green.team_f.user.model;

import lombok.Data;

@Data
public class UserSelDto {
   // private int iuser;
    private String uid;
   // private String upw;
    private String name;
    private int age;
    private double bmr;
    private int height;
    private int weight;
    private char gender;
    private String usepic;
    private String goal;
}
