package com.green.team_f.list;

import com.green.team_f.list.model.ListSelAllEntity;
import com.green.team_f.list.model.ListSelHelVo;
import com.green.team_f.list.model.ListSelMealVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListMapper {
    List<ListSelMealVo> selMealList(ListSelAllEntity entity);
    List<ListSelHelVo> selHelList(ListSelAllEntity entity);
}
