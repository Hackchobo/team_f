package com.green.team_f.user;

import com.green.team_f.user.model.*;
import com.green.team_f.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Service
public class UserSevice {
    @Value("${file.dir}")
    private String fileDir;
    private UserMapper mapper;


    @Autowired
    public UserSevice(UserMapper mapper){
        this.mapper = mapper;
    }

    public int insUser(UserInsDto dto){
        UserEntity entity = new UserEntity();
        //double bmr;
        entity.setUid(dto.getUid());
        entity.setUpw(dto.getUpw());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setHeight(dto.getHeight());
        entity.setWeight(dto.getWeight());
        //성별 대문자 변경
        char gender = Character.toUpperCase(dto.getGender());
        entity.setGender(gender);
        if (!(gender == 'M' || gender == 'F')) {
            return -1;
        }
        //기초대사량 측정
        if(entity.getGender() == 'F'){
            double bmr = Math.round((665.1+(9.56 * dto.getHeight())+(1.85 * dto.getHeight())-(4.68 * dto.getAge()))*100)/100.0;
            entity.setBmr(bmr);
        } else if(entity.getGender()== 'M'){
            entity.setBmr(66.47 +(13.75 * dto.getHeight())+(5 * dto.getHeight())-(6.76 * dto.getAge()));
        }

        return mapper.insUser(entity);
    } // 회원등록 (사진제외 NULL값으로 날라감)

    public int updUserPic(MultipartFile pic, UserPatchPicDto dto){
        String centerPath = String.format("%s/user/%d",fileDir, dto.getIuser());
        String dicPath = String.format("%s/%s", FileUtils.getAbsolutePath(fileDir), centerPath);

        File dic = new File(dicPath);           //폴더가 없을 경우 폴더를 생성
        if(!dic.exists()) {
            dic.mkdirs();
        }

        String originFileName = pic.getOriginalFilename();
        String savedFileName = FileUtils.makeRandomFileNm(originFileName);
        String savedFilePath = String.format("%s/%s", centerPath, savedFileName);
        String targetPath = String.format("%s/%s", FileUtils.getAbsolutePath(fileDir), savedFilePath);
        File target = new File(targetPath);
        try {
            pic.transferTo(target);
        }catch (Exception e) {
            return 0;
        }
        dto.setUsepic(savedFilePath);
        try {
            int result = mapper.updUserPic(dto);
            if(result == 0) {
                throw new Exception("프로필 사진을 등록할 수 없습니다.");
            }
        } catch (Exception e) {
            //파일 삭제
            target.delete();
            return 0;
        }
        return 1;
    }

    public UserSelDto selUser(UserEntity entity){
        UserSelDto dto = new UserSelDto();
        entity.setUid(dto.getUid());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setHeight(dto.getHeight());
        entity.setWeight(dto.getWeight());
        entity.setGender(dto.getGender());
        entity.setUsepic(dto.getUsepic());
        entity.setBmr(dto.getBmr());
        entity.setGoal(dto.getGoal());
        return mapper.selUser(entity);
    }

    public int delUser(UserRemoveDto dto){
        String path = String.format("%s/user/%d",fileDir, dto.getIuser());
        //String dicPath = String.format("%s", fileDir);
        /*File dic = new File(path);           //폴더가 있을 경우 폴더를 삭제
        if(dic.exists()) {
            dic.delete();
        }*/

        File folder = new File(path);
       // folder.delete();
        try {
            while(folder.exists()) {
                File[] folder_list = folder.listFiles(); //파일리스트 얻어오기

                for (int j = 0; j < folder_list.length; j++) {
                    folder_list[j].delete(); //파일 삭제
                    System.out.println("파일이 삭제되었습니다.");

                }

                if(folder_list.length == 0 && folder.isDirectory()){
                    folder.delete(); //대상폴더 삭제
                    System.out.println("폴더가 삭제되었습니다.");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return mapper.delUser(dto);
    }

    public int updUserGoal(UserPatchGoalDto dto){
        return mapper.updUserGoal(dto);
    }

    /*public UserPatchGoalVo selUserGoal(UserEntity entity){
        UserPatchGoalVo vo = new UserPatchGoalVo();
        entity.setGoal(vo.getGoal());
        return mapper.selUserGoal(entity);
    }*/


}
