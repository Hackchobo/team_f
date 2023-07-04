package com.green.team_f.list;

import com.green.team_f.list.model.InsCalenderDto;
import com.green.team_f.list.model.ListSelAllEntity;
import com.green.team_f.list.model.ListSelHelVo;
import com.green.team_f.list.model.ListSelMealVo;
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

    //TEST, user, 날짜값을 보냈을때 Long 값으로 ical 반환하는 메서드
    public Long getIcalByuserAndDate(InsCalenderDto calenderDto) {
        int result = mapper.InsCalenderDataForRec(calenderDto);
        log.info("ical : " + calenderDto.getIcal());
        return calenderDto.getIcal();
    }
}
