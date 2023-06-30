package com.green.team_f.foodrecord;


import com.green.team_f.foodrecord.model.FoodRecordEntity;
import com.green.team_f.foodrecord.model.FoodRecordInsDto;
import com.green.team_f.foodrecord.model.FoodRecordUpdDto;
import com.green.team_f.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
        int result = mapper.selIuserDate(dto.getIcal());
        if(result!=0){
            return -1;
        }

        String path = fileDir + "/"+dto.getIcal();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String randomName = FileUtil.makeRandomFileNm(img.getOriginalFilename());
        String namePath = path +"/"+ randomName;
        File file1 = new File(namePath);
        try {
            img.transferTo(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FoodRecordEntity entity=new FoodRecordEntity();
        entity.setIcal(dto.getIcal());
        entity.setIfood(dto.getIfood());
        entity.setUefTime(dto.getUefTime());
        entity.setUefPic(namePath);
        entity.setCtnt(dto.getCtnt());

        return mapper.insFoodRecord(entity);
    }

    public List<FoodRecordEntity> selRecordAll(){
        return mapper.selRecordAll();
    }


    public int updRecord(MultipartFile img, FoodRecordUpdDto dto){
        String path = fileDir + "/"+dto.getIcal();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String randomName = FileUtil.makeRandomFileNm(img.getOriginalFilename());
        String namePath = path +"/"+ randomName;
        File file1 = new File(namePath);
        try {
            img.transferTo(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FoodRecordEntity entity=new FoodRecordEntity();
        entity.setImealRecord(dto.getImealRecord());
        entity.setIfood(dto.getIfood());
        entity.setIcal(dto.getIcal());
        entity.setUefTime(dto.getUefTime());
        entity.setCtnt(dto.getCtnt());
        entity.setUefPic(namePath);
        return mapper.updRecord(entity);
    }


    public int delRecord(int imealRecord){
        return mapper.delRecord(imealRecord);
    }
}
