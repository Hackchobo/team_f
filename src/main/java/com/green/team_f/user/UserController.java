package com.green.team_f.user;

import com.green.team_f.user.model.UserEntity;
import com.green.team_f.user.model.UserInsDto;
import com.green.team_f.user.model.UserPatchPicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserSevice service;

    @Autowired
    public UserController(UserSevice service) {
        this.service = service;
    }

    @PostMapping
    public int postUser(@RequestBody UserInsDto dto){
        return service.insUser(dto);
    }

    @PatchMapping(value = "/pic", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public int patchPicUser(@RequestPart MultipartFile pic, @RequestParam int iuser){
        UserPatchPicDto dto=new UserPatchPicDto();
        dto.setIuser(iuser);
        return service.updUserPic(pic,dto);
    }

}
