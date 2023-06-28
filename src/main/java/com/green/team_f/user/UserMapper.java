package com.green.team_f.user;

import com.green.team_f.user.model.UserInsDto;
import com.green.team_f.user.model.UserPatchPicDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserInsDto dto);
    int updUserPic(UserPatchPicDto dto);
}
