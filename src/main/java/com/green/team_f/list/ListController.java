package com.green.team_f.list;

import com.green.team_f.list.model.InsCalenderDto;
import com.green.team_f.list.model.ListSelAllEntity;
import com.green.team_f.list.model.ListSelHelVo;
import com.green.team_f.list.model.ListSelMealVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "음식, 운동 리스트")
@RequestMapping("/api/list")
public class ListController {

    private ListService service;

    @Autowired
    public ListController(ListService service) {
        this.service = service;
    }

    @GetMapping("/{date}/food")
    @Operation(summary = "음식 리스트 출력",description = ""+
            "date:날짜의 값 문자열(ex:20230703)<br>"
    )
    public List<ListSelMealVo> getMeal(@PathVariable int date){
        ListSelAllEntity entity = new ListSelAllEntity();
        entity.setDate(date);
        return service.selMealList(entity);
    }

    @GetMapping("/{date}/hel")
    @Operation(summary = "운동 리스트 출력",description = ""+
            "date:날짜의 값 문자열(ex:20230703)<br>"
    )
    public List<ListSelHelVo> getHel(@PathVariable int date){
        ListSelAllEntity entity = new ListSelAllEntity();
        entity.setDate(date);
        return service.selHelList(entity);
    }


    @GetMapping("/icalTest")
    @Operation(summary = "백엔드용 메서드입니다", description ="iuser : int, recDate : yyyy-mm-dd or yyyymmdd<br>"+
            "날짜와 유저를 파라미터로 받아 캘린더 pk값을 리턴합니다<br>" +
            "달력 레코드 존재 X :  새로운 레코드 생성 후 pk리턴 <br>" +
            "달력 레코드 존재 O :  날짜,유저에 해당하는 pk 리턴<br>")
    public Long getIcal(@RequestParam int iuser, @RequestParam String recDate){
        InsCalenderDto calenderDto = new InsCalenderDto();
        calenderDto.setIuser(iuser);
        calenderDto.setRecDate(recDate);
        return service.getIcalByuserAndDate(calenderDto);
    }


}
