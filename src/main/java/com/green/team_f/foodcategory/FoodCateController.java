package com.green.team_f.foodcategory;


import com.green.team_f.foodcategory.model.FoodCateEntity;
import com.green.team_f.foodcategory.model.FoodCateInsDto;
import com.green.team_f.foodcategory.model.FoodCateUpDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/foodcate")
@RequiredArgsConstructor
public class FoodCateController {
    private final FoodCateService service;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "음식카테고리 입력",description = ""+
            "foodName : 음식의 이름<br>"+
            "f_kcal : 음식의 칼로리")
    public int postFoodCate(@RequestPart MultipartFile img, FoodCateInsDto dto) {
        return service.insFoodCate(img, dto);
    }


    @GetMapping
    @Operation(summary = "모든음식 카테고리 조회",description = ""+
    "ifood : 음식의 고유번호<br>"+
    "foodName : 음식의 이름<br>"+
    "f_kcal : 음식의 칼로리<br>"+
    "foodPic : 음식의 사진<br>")
    public List<FoodCateEntity> getFoodCate() {
        return service.selFoodCate();
    }


    @PatchMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "음식카테고리 정보수정",description = ""+
            "ifood :변경할 음식의 고유번호<br>"+
            "foodName :변경할 음식의 이름<br>"+
            "f_kcal : 변경할 음식의 칼로리")
    public int patchFoodCate(@RequestPart MultipartFile img, FoodCateUpDto dto) {
        return service.updFoodCate(img, dto);
    }

    @DeleteMapping("/{ifood}")
    @Operation(summary = "음식카테고리 삭제",description = ""+
            "ifood : 삭제할 음식의 고유번호")
    public int delete(int ifood){
        return service.delFoodCate(ifood);
    }
}

