package com.green.team_f.foodrecord;

import com.green.team_f.foodrecord.model.FoodRecordEntity;
import com.green.team_f.foodrecord.model.FoodSumList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodRecordMapper {

    int intFoodRecordDate(FoodRecordEntity entity);
    int selIfood(int ifood);
    String selCalCreated(int ical);
    List<FoodRecordEntity> selRecordAll();
    int updRecord(FoodRecordEntity entity);
    int delRecord(int imealRecord);
    List<FoodSumList> sumEatKcal(int iuser, String start, String end);
    int sumEacKcal(int ical);

}

