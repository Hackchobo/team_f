package com.green.team_f.user;

import com.green.team_f.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserEntity entity);
    int updUserPic(UserPatchPicDto dto);
    UserSelDto selUser(UserEntity entity);
    int delUser(UserRemoveDto dto);
    int updUserGoal(UserPatchGoalDto dto);
    UserPatchGoalVo selUserGoal(UserEntity entity);
}
