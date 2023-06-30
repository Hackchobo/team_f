package com.green.team_f.calender;

import com.green.team_f.calender.model.MonthRecordByUserDto;
import com.green.team_f.calender.model.MonthRecordByUserVo;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "월단위 기록리스트",description = "유저별로, 달단위 기록이 존재하는 날짜리스트를 리턴합니다<br>" +
            "month : 한자리 숫자 (1~12) " + " iuser : 회원번호")
    public List<MonthRecordByUserVo> GetMonthRecordByUser(@PathVariable Long iuser, @RequestParam int year, @RequestParam int month){
        MonthRecordByUserDto dto = new MonthRecordByUserDto();
        dto.setMonth(Integer.toString(month));
        dto.setIuser(iuser);
        dto.setYear(Integer.toString(year));
        return service.GetMonthRecordByUser(dto);
    }
}
