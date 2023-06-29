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
@RequestMapping("/exrec")
@RequiredArgsConstructor
@Slf4j
public class ExRecController {
    private final ExRecService service;

    @Tag(name="카테고리+칼로리",description = "ihelCate:운동종류pk값")
    @GetMapping("/kcalbyex")
    public int getCate(@RequestParam long ihelCate){
        SelExDto dto = new SelExDto();
        dto.setIhelCate(ihelCate);
        return service.selEx(dto);
    }
//    @Tag(name="운동기록",description = " uhPic:유저운동사진, ical:기록날짜, uhKcal:시간당소모량, ctnt:유저메모, time:운동시간(분단위)")
//    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public int postEx(@RequestPart MultipartFile uhPic, @RequestPart InsExRecDto dto, @RequestPart int time){
//        dto.setTime(time);
//        return service.InsExRec(uhPic,dto);
//    }
    @Tag(name="운동카테고리목록", description = "문자 배열 타입")
    @GetMapping("/exlist")
    public List<String> getHelCateList (){return service.getHelCateList();}

}
