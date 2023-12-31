package com.green.team_f.exrec;

import com.green.team_f.exrec.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/exrec")
@RequiredArgsConstructor
@Slf4j
@Tag(name="운동기록관리")
public class ExRecController {
    private final ExRecService service;

    @GetMapping("/kcalbyex")
    @Operation(summary = "운동종류의 분당 칼로리(ex: 100)",description =
            "ihelCate : 운동종류의 PK값")
    public int getCate(@RequestParam Long ihelCate){
        SelExDto dto = new SelExDto();
        dto.setIhelCate(ihelCate);
        return service.selEx(dto);
    }

    @Operation(summary = "운동카테고리 전체목록",description =
            " 문자 배열 타입")
    @GetMapping("/exlist")
    public List<SelListKcalVo> getHelCateList (){return service.getHelCateList();}


    @Operation(summary = "운동기록입력",description = "운동종류의 분당 칼로리(ex: 100)"+
            " uhPic:유저운동사진(이미지파일)<br>"+
            " ical:기록날짜(yyyy-mm-dd)<br>" +
            " uhKcal:시간당소모량(int)<br>" +
            " ctnt:유저메모(String, text)<br>" +
            " time:운동시간(ex:30분=30,1시간 20분 =80)")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public int postEx(@RequestPart MultipartFile uhPic,@RequestPart InsExRecDto dto) {
        return service.InsExRec(uhPic,dto);
    }

    @Operation(summary = "운동,시간 입력시 기록예정정보 출력", description =" 출력내용 : 운동이름(helName), 분당소모칼로리(hKcal), 운동시간(time), 총 소모칼로리(totalHelKcal)")
    @GetMapping("/subinfo")
    public SelSubInfoForExRecVo getSubInfoByMinAndHelCate(@RequestBody SelSubInfoForExRecDto dto){
        return service.getSubInfoByMinAndHelCate(dto);
    }


}