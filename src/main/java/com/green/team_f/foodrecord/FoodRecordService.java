package com.green.team_f.foodrecord;


import com.green.team_f.foodrecord.model.*;
import com.green.team_f.list.ListService;
import com.green.team_f.list.model.InsCalenderDto;
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
    private final ListService service;

    @Value("${file.dir}")
    private String fileDir;



    public List<FoodRecordEntity> selRecordAll(){
        return mapper.selRecordAll();
    }


    public int updRecord(MultipartFile img, FoodRecordUpdDto dto){
        String path = FileUtils.getAbsolutePath(fileDir)+"/foodrecord";
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
        entity.setUefPic(randomName);
        return mapper.updRecord(entity);
    }


    public int delRecord(int imealRecord){
        return mapper.delRecord(imealRecord);
    }


    public int sumEacKcal(int ical){
        return mapper.sumEacKcal(ical);
    }

//    public int intFoodRecordDate(@RequestBody FoodRecordInsDto dto){
//        int i = mapper.selIfood(dto.getIfood());
//        String s = mapper.selCalCreated(dto.getIcal());
//        FoodRecordEntity entity=new FoodRecordEntity();
//        switch(dto.getUefTime()){
//            case 1:
//                entity.setUefTime("아침");
//                break;
//            case 2:
//                entity.setUefTime("점심");
//                break;
//            case 3:
//                entity.setUefTime("저녁");
//                break;
//        }
//
//
//        entity.setIfood(dto.getIfood());
//        entity.setIcal(dto.getIcal());
//        entity.setCreatedAt(s);
//        entity.setUefKcal(i);
//        entity.setCtnt(dto.getCtnt());
//        mapper.intFoodRecordDate(entity);
//
//        String time = mapper.getTime(dto.getIcal());
//        System.out.println(time);
//        String recordTime = mapper.getRecordTime(dto.getIcal());
//        System.out.println(recordTime);
//        if(time.equals(recordTime)){
//            int sumKcal = mapper.sumKcal(dto.getIcal(), time);
//          return  mapper.updCalenderKcal(dto.getIcal(),sumKcal);
//        }
//        return -1;
//    }

    public FoodSum sumEatKcal(int iuser, String start, String end){
        FoodSum foodSum=new FoodSum();
        List<FoodSumList> foodSums = mapper.sumEatKcal(iuser,start,end);
        foodSum.setIuser(iuser);
        foodSum.setList(foodSums);
        return foodSum;
    }


    public int updImg(MultipartFile img,int imealRecord){
        String path = FileUtils.getAbsolutePath(fileDir)+"/foodrecord" ;
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

        return mapper.updImg(randomName,imealRecord);

    }


    public int insFoodRecord(MultipartFile img,FoodRecordInsDto dto){
        //(1)dto로 받아온 정보를 db로 넘기는 과정
        //ifood를 기반으로 음식당 칼로리얻기
        int ifoodKcal = mapper.selIfood(dto.getIfood());

        //(1-1)iuser + recDate 로 존재여무 판단해서 ical 리턴
        InsCalenderDto insCalenderDto = new InsCalenderDto();
        insCalenderDto.setIuser(dto.getIuser());
        insCalenderDto.setRecDate(dto.getRecDate());
        Long ical = service.getIcalByuserAndDate(insCalenderDto);

//        String s = mapper.selCalCreated(dto.getIcal());
        String path = FileUtils.getAbsolutePath(fileDir)+"/foodrecord";

        //(2)파일 저장경로 폴더 여부 확인후 없으면 생성
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        //(3) 업로드된 파일 랜덤이름+ext 생성
        String randomName = FileUtils.makeRandomFileNm(img.getOriginalFilename());
        String namePath = path +"/"+ randomName;
        File file1 = new File(namePath);
        //(3-1) 파일 전송

        try {
            img.transferTo(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //받아온 숫자에 따라 문자열로 변환해 저장

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
        entity.setIcal(ical);
        entity.setUefKcal(ifoodKcal);
        entity.setCtnt(dto.getCtnt());
        entity.setUefPic(randomName);

        entity.setCreatedAt(dto.getRecDate());

//recDate가 있어서 따로 time을 받아올 필요가 없음. 주석처리.
//        String time = mapper.getTime(dto.getIcal());
//        entity.setCreatedAt(time);
//        System.out.println(time);

        //(foodrecord 테이블에 최종 정보가 저장되는 부분

        mapper.insFoodRecord(entity);

//
//        String recordTime = mapper.getRecordTime(dto.getIcal());
//        System.out.println(recordTime);
//        if(time.equals(recordTime)){
//            int sumKcal = mapper.sumKcal(dto.getIcal(), time);}

        return  mapper.updCalenderKcal(ical,ifoodKcal);
    }
}
