package com.qtu404.present.user.service.impl;

import com.qtu404.present.unit.ImageDeal;
import com.qtu404.present.user.vo.UserVo;
import com.qtu404.present.user.dao.UserDao;
import com.qtu404.present.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String addNewUser(UserVo user) {
        return userDao.addNewUser(user);
    }

    @Override
    public UserVo fetchUserByPhone(String phoneNum) {
        return userDao.fetchUserByPhone(phoneNum);
    }

    @Override
    public UserVo fetchUserByLogin(String phoneNum, String password) {
        return userDao.fetchUserByLogin(phoneNum, password);
    }

    @Override
    public UserVo fetchUserById(String userid) {
        return userDao.fetchUserById(userid);
    }

    @Override
    public String modifyAvator(UserVo userVo, String avatorImgData, String realPath) {
        String rst = "fail";
        String imgPath = ImageDeal.getAvatorPath(realPath, userVo.getUserId());
        ImageDeal.GenerateImage(avatorImgData, imgPath);
        userVo.setAvator(ImageDeal.getUserAvatorUrl(userVo.getUserId()));
        int changeRow = userDao.modifyUser(userVo);
        if (changeRow == 1) {
            rst = "success";
        }
        return rst;
    }

}
