package com.qtu404.present.slide.service.impl;

import com.qtu404.present.log.dao.LogDao;
import com.qtu404.present.log.vo.LogVo;
import com.qtu404.present.slide.vo.SlideVo;
import com.qtu404.present.slide.dao.SlideDao;
import com.qtu404.present.slide.service.SlideService;
import com.qtu404.present.user.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service("slideService")
public class SlideServiceImpl implements SlideService {
    @Resource(name = "slideDao")
    private SlideDao slideDao;

    @Override
    public int modifyContent(UserVo userVo, SlideVo slideVo) {
        String userId = slideDao.fetchSlideById(slideVo.getSlideId()).getUserId();
        if (userId.equals(userVo.getUserId())) {//保证是自己的
            return slideDao.modifyContent(slideVo);
        } else {
            return 0;
        }
    }

    @Override
    public int addNewSlide(String userId) {
        return slideDao.addNewSlide(userId);
    }

    @Override
    public SlideVo fetchSlideById(UserVo userVo, String slideId) {
        SlideVo slideVo = slideDao.fetchSlideById(slideId);
        if (slideVo != null) {
            if (userVo != null && userVo.getUserId().equals(slideVo.getUserId())) {
                slideVo.setWhoPlay("owner");
            } else {
                slideVo.setWhoPlay("visitor");
            }
        } else {
            return null;
        }
        return slideVo;
    }

    @Override
    public int delSlideById(UserVo userVo, String slideId) {
        SlideVo slideVo = slideDao.fetchSlideById(slideId);
        if (slideVo.getUserId().equals(userVo.getUserId())) {//保证是自己的
            return slideDao.delSlideById(slideId);
        } else {
            return 0;
        }
    }

    @Override
    public int modifySlideName(UserVo userVo, String slideId, String slideName) {
        SlideVo slideVo = slideDao.fetchSlideById(slideId);
        if (slideVo.getUserId().equals(userVo.getUserId())) {//保证是自己的
            return slideDao.modifySlideName(slideId, slideName);
        } else {
            return 0;
        }
    }

    public SlideDao getSlideDao() {
        return slideDao;
    }

    public void setSlideDao(SlideDao slideDao) {
        this.slideDao = slideDao;
    }

    @Override
    public ArrayList<SlideVo> findAllSlideByUserId(String userId) {
        return slideDao.findAllSlideByUserId(userId);
    }
}
