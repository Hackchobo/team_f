package com.green.team_f.user;

import com.green.team_f.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name="회원가입 페이지")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserSevice service;

    @Autowired
    public UserController(UserSevice service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "회원가입 페이지",description = ""+
            "uid:[string]회원 ID<br>"+
            "upw:[string]회원 PW<br>"+
            "name:[string]회원 이름<br>"+
            "age:[int]회원 나이<br>"+
            "height:[int]회원 키<br>"+
            "weight:[int]회원 몸무게<br>"+
            "gender:[string]회원 성별"
    )
    public int postUser(@RequestBody UserInsDto dto){
        return service.insUser(dto);
    }

    @PatchMapping(value = "/pic", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "회원사진등록",description = ""+
            "iuser:회원의 PK값(몇번째 등록된사람인지)<br>"+
            "pic:[string]회원 프로필에 올릴 사진<br>"
    )
    public int patchPicUser(@RequestPart MultipartFile pic, @RequestParam int iuser){
        UserPatchPicDto dto=new UserPatchPicDto();
        dto.setIuser(iuser);
        return service.updUserPic(pic,dto);
    }

    @GetMapping("/{iuser}")
    @Operation(summary = "회원 정보 보기",description = ""+
            "iuser:회원의 PK값(몇번째 등록된 사람인지)<br>"
    )
    public UserSelDto getUser(@PathVariable int iuser){
        UserEntity entity = new UserEntity();
        entity.setIuser(iuser);
        return service.selUser(entity);
    }

    @DeleteMapping
    @Operation(summary = "회원 탈퇴",description = ""+
            "iuser:회원의 PK값(몇번째 등록된 사람인지)<br>"
    )
    public int delUser(@RequestParam int iuser){
        UserRemoveDto dto = new UserRemoveDto();
        dto.setIuser(iuser);
        return service.delUser(dto);
    }

    @PatchMapping
    @Operation(summary = "회원의 목표글올리기",description = ""+
            "iuser:회원의 PK값(몇번째 등록된 사람인지)<br>" +
            "goal: 목표를 적으시오"
    )
    public int goalPatch(@RequestBody UserPatchGoalDto dto){
        return service.updUserGoal(dto);
    }

    @GetMapping("/{iuser}/goal")
    @Operation(summary = "회원의 목표표시",description = ""+
            "iuser:회원의 PK값(몇번째 등록된 사람인지)<br>"
    )
    public UserPatchGoalVo selUser(@PathVariable int iuser){
        UserEntity entity = new UserEntity();
        entity.setIuser(iuser);
        return service.selUserGoal(entity);
    }

}
