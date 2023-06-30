package com.green.team_f.exrec;

import com.green.team_f.exrec.model.InsExRecDto;
import com.green.team_f.exrec.model.SelExDto;
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

    @Tag(name="카테고리+칼로리",description = "ihelCate:운동종류pk값")
    @GetMapping("/kcalbyex")
    public int getCate(@RequestParam Long ihelCate){
        SelExDto dto = new SelExDto();
        dto.setIhelCate(ihelCate);
        return service.selEx(dto);
    }
    @Tag(name="운동기록입력",description = " uhPic:유저운동사진(이미지파일), ical:기록날짜(yyyy-mm-dd), uhKcal:시간당소모량(int), ctnt:유저메모(String, text), time:운동시간(ex:30분=30,1시간 20분 =80)")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public int postEx(@RequestPart MultipartFile uhPic,@RequestPart InsExRecDto dto) {
        return service.InsExRec(uhPic,dto);
    }
    @Tag(name="운동카테고리목록", description = "문자 배열 타입")
    @GetMapping("/exlist")
    public List<String> getHelCateList (){return service.getHelCateList();}

}
