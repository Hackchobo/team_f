package com.green.team_f.exrec.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InsExRecDto {
    private long iuser;
    private String recDate;
    private long ihelCate;
    private String ctnt;
    private int time;
}
