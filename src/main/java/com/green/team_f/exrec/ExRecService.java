package com.green.team_f.exrec;


import com.green.team_f.exrec.model.InsExRecDto;
import com.green.team_f.exrec.model.SelExDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class ExRecService {
    private final ExRecMapper mapper;

    public int selEx(SelExDto dto){
        return mapper.selEx(dto);
    }

    public List<String> getHelCateList (){return mapper.getHelCateList();}

//    public int InsExRec(MultipartFile uhPic, InsExRecDto dto){
//        SelExDto sDto = new SelExDto();
//        sDto.setIhelCate(dto.getIhelCate());
//        int perkcal = selEx(sDto); //칼로리
//        return mapper.InsExRec(dto);
//    }
}
