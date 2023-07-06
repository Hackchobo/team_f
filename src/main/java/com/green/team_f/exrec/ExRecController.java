package com.green.team_f.exrec;

import com.green.team_f.exrec.model.InsExRecDto;
import com.green.team_f.exrec.model.SelExDto;
import io.swagger.v3.oas.annotations.Operation;
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
public class ExRecController {
    private final ExRecService service;

    @Tag(name="카테고리+칼로리")
    @GetMapping("/kcalbyex")
    @Operation(summary = "운동종류의 분당 칼로리(ex: 100)",description = ""+
            "ihelCate : 운동종류의 PK값")
    public int getCate(@RequestParam Long ihelCate){
        SelExDto dto = new SelExDto();
        dto.setIhelCate(ihelCate);
        return service.selEx(dto);
    }


    @Tag(name="운동카테고리목록")
    @Operation(summary = "운동카테고리 전체목록",description = ""+
            " 문자 배열 타입")
    @GetMapping("/exlist")
    public List<String> getHelCateList (){return service.getHelCateList();}


    @Tag(name="운동기록입력")
    @Operation(summary = "운동종류의 분당 칼로리(ex: 100)",description = ""+
            " uhPic:유저운동사진(이미지파일)<br>"+
            " ical:기록날짜(yyyy-mm-dd)<br>" +
            " uhKcal:시간당소모량(int)<br>" +
            " ctnt:유저메모(String, text)<br>" +
            " time:운동시간(ex:30분=30,1시간 20분 =80)")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public int postEx(@RequestPart MultipartFile uhPic,@RequestPart InsExRecDto dto) {
        return service.InsExRec(uhPic,dto);
    }


}
