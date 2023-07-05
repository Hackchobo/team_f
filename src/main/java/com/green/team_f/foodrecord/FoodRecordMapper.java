package com.green.team_f.foodrecord;

import com.green.team_f.foodrecord.model.FoodRecordEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodRecordMapper {
    int insFoodRecord(FoodRecordEntity entity);
    int intFoodRecordDate(FoodRecordEntity entity);
    int selIfood(int ifood);
    List<FoodRecordEntity> selRecordAll();
    int updRecord(FoodRecordEntity entity);
    int delRecord(int imealRecord);
    int selIuserDate(int ical);

    int sumEacKcal(int ical);

}

