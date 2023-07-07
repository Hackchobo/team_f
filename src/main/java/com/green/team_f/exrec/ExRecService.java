package com.green.team_f.exrec;

import com.green.team_f.exrec.model.*;
import com.green.team_f.list.ListService;
import com.green.team_f.list.model.InsCalenderDto;
import com.green.team_f.util.FileUtils;
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
    private final ListService listService;
    private final ExRecMapper mapper;
    @Value("${file.dir}")
    private String fileDir;

    public int selEx(SelExDto dto){
        return mapper.selEx(dto);
    }

    public List<SelListKcalVo> getHelCateList (){return mapper.getHelCateList();}

    public int InsExRec(MultipartFile uhPic, InsExRecDto dto){
        //해당 iuser 값과 날짜값으로 ical 데이터가 존재하는지 여부를 확인
        //icalForUserOftheDay = ical 값이 담겨진다.

        InsCalenderDto insCalenderDto = new InsCalenderDto();
        insCalenderDto.setRecDate(dto.getRecDate());
        insCalenderDto.setIuser(dto.getIuser());
        Long icalForUserOftheDay = listService.getIcalByuserAndDate(insCalenderDto);
        //mapper.getIcalByuserAndDate(entity);
        //캘린더에 값이 없을 경우 입력하기 위한 정보 입력

        InsCalenderDto calenderDto = new InsCalenderDto();
        calenderDto.setIuser(dto.getIuser());
        calenderDto.setRecDate(dto.getRecDate());

        //캘린더
        //운동pk를 보내서 입력된 운동 별 칼로리 얻기
        SelExDto sDto = new SelExDto();
        sDto.setIhelCate(dto.getIhelCate());
        int exKcalPerMin = selEx(sDto);

        //소모칼로리 : 분당 소모칼로리 * 운동시간 = 총 소모칼로리
        int subTotalKcal = exKcalPerMin * dto.getTime();

        //dto - > dto2  요구값 -> DB저장 부분 + ical 부분 주입
        InsExRecDto2 dto2 = new InsExRecDto2();
        dto2.setIhelCate(dto.getIhelCate());
        dto2.setUhKcal(subTotalKcal);
        dto2.setCtnt(dto.getCtnt());
        dto2.setTime(dto.getTime());
        dto2.setIcal(icalForUserOftheDay);


        //파일을 저장하는 부분
        String savedName = FileUtils.makeRandomFileNm(uhPic.getOriginalFilename());
        dto2.setUhPic(savedName);
        //최종 데이터 저장 부분
        mapper.InsExRec(dto2);

        //달력업데이트 부분
        UpdCalByExRecDto updCalByExRecDto = new UpdCalByExRecDto();
        updCalByExRecDto.setIcal(icalForUserOftheDay);
        updCalByExRecDto.setIuser(dto.getIuser());
        updCalByExRecDto.setRecDate(dto.getRecDate());
        updCalByExRecDto.setUhKcal(subTotalKcal);

        int updCalByExRecResult = mapper.upCalenderByHelRec(updCalByExRecDto);

        //path, 디렉토리명 만들기
        //파일전송
        String dirPath = FileUtils.getAbsolutePath(fileDir)+"/helrecord" ;
        File file = new File(dirPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String targetPath = (dirPath +"/"+ savedName);
        File target = new File(targetPath);

        try{
            uhPic.transferTo(target);
        }catch(IOException e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public SelSubInfoForExRecVo getSubInfoByMinAndHelCate(SelSubInfoForExRecDto dto){

        SelSubInfoForExRecVo vo = new SelSubInfoForExRecVo();
        //(1)운동시간 vo로 전달
        vo.setTime(dto.getTime());

        //(2)운동pk로 운동이름얻기
        SelExDto sDto = new SelExDto();
        sDto.setIhelCate(dto.getIhelCate());
        vo.setHelName(mapper.selExName(sDto));

        log.info(mapper.selExName(sDto));
        //(3)운동pk를 보내서 입력된 운동 별 칼로리 얻기
        int exKcalPerMin = selEx(sDto);
        vo.setHKcal(exKcalPerMin);
        log.info(Integer.toString(exKcalPerMin));

        //(4)소모칼로리 : 분당 소모칼로리 * 운동시간 = 총 소모칼로리
        int subTotalKcal = exKcalPerMin * dto.getTime();
        vo.setTotalHelKcal(subTotalKcal);
        log.info(Integer.toString(subTotalKcal));
        return vo;
    }

}
