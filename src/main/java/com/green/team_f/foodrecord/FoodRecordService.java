package com.green.team_f.foodrecord;


import com.green.team_f.foodrecord.model.*;
import com.green.team_f.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodRecordService {

    private final FoodRecordMapper mapper;

    @Value("${file20.dir}")
    private String fileDir;

    public int insFoodRecord(MultipartFile img, FoodRecordInsDto dto){

        String path = FileUtils.getAbsolutePath(fileDir) + "/"+dto.getIcal();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String randomName = FileUtils.makeRandomFileNm(img.getOriginalFilename());
        String namePath = path +"/"+ randomName;
        File file1 = new File(namePath);
        try {
            img.transferTo(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FoodRecordEntity entity=new FoodRecordEntity();
        switch(dto.getUefTime()){
            case 1:
                entity.setUefTime("아침");
                break;
            case 2:
                entity.setUefTime("점심");
                break;
            case 3:
                entity.setUefTime("저녁");
                break;
        }
        entity.setIcal(dto.getIcal());
        entity.setIfood(dto.getIfood());

        entity.setUefPic(namePath);
        entity.setCtnt(dto.getCtnt());

        return mapper.insFoodRecord(entity);
    }

    public List<FoodRecordEntity> selRecordAll(){
        return mapper.selRecordAll();
    }


    public int updRecord(MultipartFile img, FoodRecordUpdDto dto){
        String path = FileUtils.getAbsolutePath(fileDir) + "/"+dto.getIcal();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String randomName = FileUtils.makeRandomFileNm(img.getOriginalFilename());
        String namePath = path +"/"+ randomName;
        File file1 = new File(namePath);
        try {
            img.transferTo(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FoodRecordEntity entity=new FoodRecordEntity();
        switch(dto.getUefTime()){
            case 1:
                entity.setUefTime("아침");
                break;
            case 2:
                entity.setUefTime("점심");
                break;
            case 3:
                entity.setUefTime("저녁");
                break;
        }
        entity.setImealRecord(dto.getImealRecord());
        entity.setIfood(dto.getIfood());
        entity.setIcal(dto.getIcal());

        entity.setCtnt(dto.getCtnt());
        entity.setUefPic(namePath);
        return mapper.updRecord(entity);
    }


    public int delRecord(int imealRecord){
        return mapper.delRecord(imealRecord);
    }


    public int sumEacKcal(int ical){
        return mapper.sumEacKcal(ical);
    }

    public int intFoodRecordDate(@RequestBody FoodRecordInsDto dto){
        int i = mapper.selIfood(dto.getIfood());
        String s = mapper.selCalCreated(dto.getIcal());
        FoodRecordEntity entity=new FoodRecordEntity();
        switch(dto.getUefTime()){
            case 1:
                entity.setUefTime("아침");
                break;
            case 2:
                entity.setUefTime("점심");
                break;
            case 3:
                entity.setUefTime("저녁");
                break;
        }
        entity.setIfood(dto.getIfood());
        entity.setIcal(dto.getIcal());
        entity.setCreatedAt(s);
        entity.setUefKcal(i);
        entity.setCtnt(dto.getCtnt());
        return mapper.intFoodRecordDate(entity);
    }

    public FoodSum sumEatKcal(int iuser, String start, String end){
        FoodSum foodSum=new FoodSum();
        List<FoodSumList> foodSums = mapper.sumEatKcal(iuser,start,end);
        foodSum.setIuser(iuser);
        foodSum.setList(foodSums);
        return foodSum;
    }
}
