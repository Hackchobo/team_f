package com.green.team_f.foodrecord.model;

import lombok.Data;

import java.util.List;

@Data
public class FoodSum {

    private int iuser;
    private List<FoodSumList> list;
}
