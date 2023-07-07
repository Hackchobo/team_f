package com.green.team_f.calender;

import com.green.team_f.calender.model.MonthRecordByUserDto;
import com.green.team_f.calender.model.MonthRecordByUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calender")
@RequiredArgsConstructor
@Slf4j
public class CalenderController {
    private final CalenderService service;

    @GetMapping("/{iuser}")
    @Tag(name = "캘린더 리스트")
    @Operation(summary = "월단위 기록리스트",description = "유저별로, 달단위 기록이 존재하는 날짜리스트를 리턴합니다<br>" +
            "month : 연도 및 월 정보(yyyymm)) " + " iuser : 회원번호<br>"+
            "결과예시 : 'dates : 2023-06-06' ")
    public List<MonthRecordByUserVo> GetMonthRecordByUser(@PathVariable Long iuser, @RequestParam int yearMonth){
        MonthRecordByUserDto dto = new MonthRecordByUserDto();
        dto.setYearMonth(Integer.toString(yearMonth));
        dto.setIuser(iuser);
        return service.GetMonthRecordByUser(dto);
    }
}
