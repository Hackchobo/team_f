package com.green.team_f.exrec;


import com.green.team_f.exrec.model.InsExRecDto;
import com.green.team_f.exrec.model.InsExRecDto2;
import com.green.team_f.exrec.model.SelExDto;
import com.green.team_f.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class ExRecService {
    private final ExRecMapper mapper;
    @Value("${file3.dir}")
    private String filedir;

    public int selEx(SelExDto dto){
        return mapper.selEx(dto);
    }

    public List<String> getHelCateList (){return mapper.getHelCateList();}

    public int InsExRec(MultipartFile uhPic, InsExRecDto dto){
        InsExRecDto2 dto2 = new InsExRecDto2();
        SelExDto sDto = new SelExDto();

        sDto.setIhelCate(dto.getIhelCate());
        dto2.setIhelCate(dto.getIhelCate());
        int exKcalPerMin = selEx(sDto);//운동pk를 보내서 입력된 운동 별 칼로리 얻기

        dto2.setIcal(dto.getIcal());
        dto2.setUhKcal((long)exKcalPerMin * dto.getTime()); //분당 소모칼로리 * 운동시간
        dto2.setCtnt(dto.getCtnt());
        dto2.setTime(dto.getTime());

        //파일을 저장하는 부분
        String savedName = FileUtil.makeRandomFileNm(uhPic.getOriginalFilename());
        dto2.setUhPic(savedName);

        mapper.InsExRec(dto2);


        //path, 디렉토리명 만들기
        String dirPath = String.format("%s/health/%s",filedir,dto.getIcal());


        File file = new File(dirPath);

        if(!(file.exists())){
            file.mkdirs();
        }
        String targetPath = String.format("%s/%s",dirPath,savedName);
        File target = new File(targetPath);

        try{
            uhPic.transferTo(target);
        }catch(IOException e){
            e.printStackTrace();
            return 0;}
        return 1;
    }

}
