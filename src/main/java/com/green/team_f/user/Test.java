package com.green.team_f.user;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class Test {
    @Value("D:/download/F_Hpic")
    public static String fileDir;

    public static void main(String[] args) {

        String path = String.format("D:/download/F_Hpic/user/4");
        File folder = new File(path);
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
    }
}
