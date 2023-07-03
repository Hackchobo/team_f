package com.green.team_f.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "메인 페이지")
    @Operation(summary = "접속한 날짜의 칼로리기록",description = "잔여칼로리,소모칼로리"+
            "iuser : 해당 유저의 PK")
    GetDataOfTodayVo GetDataOfToday(@PathVariable Long iuser){
        GetDataOfTodayDto dto = new GetDataOfTodayDto();
        dto.setIuser(iuser);
        return service.GetDataOfToday(dto);
    }
}
