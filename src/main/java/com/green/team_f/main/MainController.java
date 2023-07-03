package com.green.team_f.main;

import com.green.team_f.main.model.GetDataOfTodayDto;
import com.green.team_f.main.model.GetDataOfTodayVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MainService service;


    @PostMapping


    @GetMapping("/{iuser}")
    GetDataOfTodayVo GetDataOfToday(@PathVariable Long iuser){
        GetDataOfTodayDto dto = new GetDataOfTodayDto();
        dto.setIuser(iuser);
        return service.GetDataOfToday(dto);
    }




}
