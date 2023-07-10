package com.green.team_f.main;

import com.green.team_f.main.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    GetDataOfTodayVo GetDataOfToday(GetDataOfTodayDto dto);

    List<GetGraphDataVo> GetGraphDataByDate(GetGraphDataDto dto);
}
