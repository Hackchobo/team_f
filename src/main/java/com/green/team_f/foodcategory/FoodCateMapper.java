package com.green.team_f.foodcategory;

import com.green.team_f.foodcategory.model.FoodCateEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodCateMapper {

    int insFoodCate(FoodCateEntity entity);
    List<FoodCateEntity> selFoodCate();
    int updFoodCate(FoodCateEntity entity);
    int delFoodCate(int ifood);
}
