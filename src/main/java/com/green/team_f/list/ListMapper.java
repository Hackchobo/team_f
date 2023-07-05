package com.green.team_f.list;

import com.green.team_f.list.model.InsCalenderDto;
import com.green.team_f.list.model.ListSelAllEntity;
import com.green.team_f.list.model.ListSelHelVo;
import com.green.team_f.list.model.ListSelMealVo;
import com.green.team_f.main.model.GetDataOfTodayDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListMapper {
    List<ListSelMealVo> selMealList(ListSelAllEntity entity);
    List<ListSelHelVo> selHelList(ListSelAllEntity entity);

    int InsCalenderDataForRec(InsCalenderDto dto);
    int getIuserBmr(GetDataOfTodayDto dto);
}
