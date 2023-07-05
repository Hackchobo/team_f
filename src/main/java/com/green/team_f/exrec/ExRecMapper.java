package com.green.team_f.exrec;

import com.green.team_f.exrec.model.InsExRecDto;
import com.green.team_f.exrec.model.InsExRecDto2;
import com.green.team_f.exrec.model.SelExDto;
import com.green.team_f.exrec.model.UpdCalByExRecDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ExRecMapper {
     int selEx(SelExDto dto);
//     int InsExRec(InsExRecDto dto);
     List<String> getHelCateList();
     int InsExRec(InsExRecDto2 dto2);
     int upCalenderByHelRec(UpdCalByExRecDto dto);
}
