package com.green.team_f.foodcategory;

import com.green.team_f.foodcategory.model.FoodCateEntity;
import com.green.team_f.foodcategory.model.FoodCateInsDto;
import com.green.team_f.foodcategory.model.FoodCateUpDto;
import com.green.team_f.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCateService {
    private final FoodCateMapper mapper;

    @Value("${file.dir}")
    private String fileDir;

    public int insFoodCate(MultipartFile img, FoodCateInsDto dto) {

       String path = FileUtils.getAbsolutePath(fileDir)+"/foodcate" ;
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
       FoodCateEntity entity = new FoodCateEntity();
       entity.setFoodName(dto.getFoodName());
       entity.setF_kcal(dto.getF_kcal());
       System.out.println(dto.getF_kcal());
       entity.setFoodPic(randomName);
       return mapper.insFoodCate(entity);
   }


    public List<FoodCateEntity> selFoodCate() {
        return mapper.selFoodCate();
    }

    public int updFoodCate(MultipartFile img, FoodCateUpDto dto){
        String path = FileUtils.getAbsolutePath(fileDir) +"/foodcate";
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
        FoodCateEntity entity = new FoodCateEntity();
        entity.setIfood(dto.getIfood());
        entity.setFoodName(dto.getFoodName());
        entity.setF_kcal(dto.getF_kcal());
        entity.setFoodPic(randomName);

        return mapper.updFoodCate(entity);
    }

    public int delFoodCate(int ifood){
        return mapper.delFoodCate(ifood);
    }
}
