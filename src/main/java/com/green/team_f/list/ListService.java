package com.green.team_f.list;

import com.green.team_f.list.model.InsCalenderDto;
import com.green.team_f.list.model.ListSelAllEntity;
import com.green.team_f.list.model.ListSelHelVo;
import com.green.team_f.list.model.ListSelMealVo;
import com.green.team_f.main.model.GetDataOfTodayDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
public class ListService {

    private ListMapper mapper;

    @Autowired
    public ListService(ListMapper mapper) {
        this.mapper = mapper;
    }

    public List<ListSelMealVo> selMealList(ListSelAllEntity entity) {
        return mapper.selMealList(entity);
    }

    public List<ListSelHelVo> selHelList(ListSelAllEntity entity) {
        return mapper.selHelList(entity);
    }

    //(1) user, 날짜값을 보냈을때 Long 값으로 ical 반환
    //(2) 캘린더 최초 기록 삽입시 회원의 bmr수치 반영,
    // 업데이트시에는 bmr수치 미반영할 수 있음.
    public Long getIcalByuserAndDate(InsCalenderDto insCalenderDto) {
        //iuser 별 BMR받아오기
        GetDataOfTodayDto getDataOfTodayDto = new GetDataOfTodayDto();
        getDataOfTodayDto.setIuser(insCalenderDto.getIuser());
        //calenderDto에 bmr 주입
        int iuserBmr = mapper.getIuserBmr(getDataOfTodayDto);
        insCalenderDto.setBmr(iuserBmr);
        mapper.InsCalenderDataForRec(insCalenderDto);
        log.info("ical : " + insCalenderDto.getIcal());
        log.info("bmr : "+ iuserBmr);
        return insCalenderDto.getIcal();
    }

}
