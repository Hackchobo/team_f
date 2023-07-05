package com.green.team_f.foodrecord;


import com.green.team_f.foodrecord.model.FoodRecordEntity;
import com.green.team_f.foodrecord.model.FoodRecordInsDto;
import com.green.team_f.foodrecord.model.FoodRecordUpdDto;
import com.green.team_f.foodrecord.model.FoodSum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "식사기록")
@RestController
@RequestMapping("/api/foodrecord")
@RequiredArgsConstructor
public class FoodRecordController {

    private final FoodRecordService service;


    @PostMapping(value = "/{iuser}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "유저 식사기록 입력",description = ""+
            "ifood : 음식의 고유번호<br>"+
            "ical : 캘린더의 고유번호<br>"+
            "uef_time : 1(아침),2(점심),3(저녁)<br>"+
            "ctnt : 음식기록 페이지의 코멘트<br><br>"+

            "-1이 넘어오면 식사기록에 이미 값이 있다")
    public int postRecord(@RequestPart MultipartFile img , FoodRecordInsDto dto){
        return service.insFoodRecord(img,dto);
    }

    @GetMapping
    @Operation(summary = "모든유저 식사기록 정보",description = ""+
            "imeal_record : 식사기록 고유번호<br>"+
            "ifood : 음식 고유번호<br>"+
            "cal : 캘린더 고유번호<br>"+
            "uef_time : 1(아침),2(점심),3(저녁)<br>"+
            "ctnt : 식사기록의 코멘트<br>"+
            "uef_pic : 유저의 음식사진 기록")
    public List<FoodRecordEntity> getRecordAll(){
        return service.selRecordAll();
    }

    @PatchMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "유저의 식사기록 변경",description = ""+
            "imealRecord : 식사기록 고유번호<br>"+
            "ifood : 음식 고유번호<br>"+
            "cal : 캘린더 고유번호<br>"+
            "uefTime : 1(아침),2(점심),3(저녁)<br>"+
            "ctnt : 식사기록의 코멘트<br>")
    public int patchRecord(@RequestPart MultipartFile img, FoodRecordUpdDto dto){
        return service.updRecord(img,dto);
    }

    @DeleteMapping("/{imealRecord}")
    @Operation(summary = "유저의 식사기록 삭제",description = ""+
            "imealRecord :유저의 식사기록 고유번호")
    public int deleteRecord(int imealRecord){
        return service.delRecord(imealRecord);
    }



    @GetMapping("/{iuser}")
    @Operation(summary = "음식의 총칼로리",description = "ResponseBody의 값이 해당유저의 하루 총 섭취칼로리")
    public FoodSum getTotalEatKcal(@PathVariable int iuser, @RequestParam String start, @RequestParam String end){
        return service.sumEatKcal(iuser,start,end);
    }

    @PostMapping
    @Operation(summary = "유저 식사기록 입력",description = ""+
            "ifood : 음식의 고유번호<br>"+
            "ical : 캘린더의 고유번호<br>"+
            "uef_time : 1(아침),2(점심),3(저녁)<br>"+
            "ctnt : 음식기록 페이지의 코멘트<br><br>")
    public int postRecordDate(@RequestBody FoodRecordInsDto dto){
        return service.intFoodRecordDate(dto);
    }


}
