package com.green.team_f.user;

import com.green.team_f.user.model.UserEntity;
import com.green.team_f.user.model.UserInsDto;
import com.green.team_f.user.model.UserPatchPicDto;
import com.green.team_f.user.model.UserRemoveDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserInsDto dto);
    int updUserPic(UserPatchPicDto dto);
    UserEntity selUser(UserEntity entity);
    int delUser(UserRemoveDto dto);
}
