package com.green.team_f.list;

import com.green.team_f.list.model.ListSelAllEntity;
import com.green.team_f.list.model.ListSelHelVo;
import com.green.team_f.list.model.ListSelMealVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/List")
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
}
