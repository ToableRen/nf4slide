package com.qtu404.present.user.service;

import com.qtu404.present.user.vo.UserVo;

public interface UserService {
    public String addNewUser(UserVo user);

    public UserVo fetchUserByPhone(String phoneNum);

    public UserVo fetchUserByLogin(String phoneNum, String password);
}
