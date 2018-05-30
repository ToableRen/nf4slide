package com.qtu404.present.user.dao;

import com.qtu404.present.user.vo.UserVo;

public interface UserDao {
    public String addNewUser(UserVo user);

    public UserVo fetchUserByPhone(String phoneNum);

    public UserVo fetchUserByLogin(String phoneNum, String password);
}
