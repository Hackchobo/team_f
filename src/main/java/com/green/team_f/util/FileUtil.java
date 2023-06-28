package com.green.team_f.util;

import java.util.UUID;

public class FileUtil {


    public static String getExt(String ext) {
        return ext.substring(ext.lastIndexOf(".")+1);
    } // 확장자를 뽑아 내는 부분

    public static String makeRandomFileNm(String fileNm){
        return UUID.randomUUID()+"."+getExt(fileNm);
    } // UUID를 사용한 랜덤한 이름을 생성하고 getExt를 이용하여 확장자까지 넣는 부분
}
