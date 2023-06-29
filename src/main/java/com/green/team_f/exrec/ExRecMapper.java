package com.green.team_f.exrec;

import com.green.team_f.exrec.model.InsExRecDto;
import com.green.team_f.exrec.model.SelExDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ExRecMapper {
     int selEx(SelExDto dto);
//     int InsExRec(InsExRecDto dto);
     List<String> getHelCateList();
}
