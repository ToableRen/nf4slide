package com.qtu404.present.user.service;

import com.qtu404.present.user.vo.UserVo;

public interface UserService {
    String addNewUser(UserVo user);

    UserVo fetchUserByPhone(String phoneNum);

    UserVo fetchUserByLogin(String phoneNum, String password);

    String modifyAvator(UserVo userVo, String avatorImgData, String realPath);

    UserVo fetchUserById(String userid);
}
