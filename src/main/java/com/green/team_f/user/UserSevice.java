package com.green.team_f.user;

import com.green.team_f.user.model.UserInsDto;
import com.green.team_f.user.model.UserPatchPicDto;
import com.green.team_f.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class UserSevice {
    @Value("D:/download/F_Hpic")
    private String fileDir;
    private UserMapper mapper;


    @Autowired
    public UserSevice(UserMapper mapper){
        this.mapper = mapper;
    }

    public int insUser(UserInsDto dto){
        return mapper.insUser(dto);
    }

    public int updUserPic(MultipartFile pic, UserPatchPicDto dto){
        String centerPath = String.format("user/%d", dto.getIuser());
        String dicPath = String.format("%s/%s", fileDir, centerPath);

        File dic = new File(dicPath);
        if(!dic.exists()) {
            dic.mkdirs();
        }

        String originFileName = pic.getOriginalFilename();
        String savedFileName = FileUtil.makeRandomFileNm(originFileName);
        String savedFilePath = String.format("%s/%s", centerPath, savedFileName);
        String targetPath = String.format("%s/%s", fileDir, savedFilePath);
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


}
