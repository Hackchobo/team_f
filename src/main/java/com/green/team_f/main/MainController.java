package com.green.team_f.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MainService service;

    @GetMapping("/{iuser}")
    GetDataOfTodayVo GetDataOfToday(@PathVariable Long iuser){
        GetDataOfTodayDto dto = new GetDataOfTodayDto();
        dto.setIuser(iuser);
        return service.GetDataOfToday(dto);
    }
}
