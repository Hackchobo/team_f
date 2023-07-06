package com.green.team_f.exrec;

import com.green.team_f.exrec.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ExRecMapper {
     int selEx(SelExDto dto);
//     int InsExRec(InsExRecDto dto);
     List<SelListKcalVo> getHelCateList();
     int InsExRec(InsExRecDto2 dto2);
     int upCalenderByHelRec(UpdCalByExRecDto dto);
     String selExName(SelExDto dto);
}
