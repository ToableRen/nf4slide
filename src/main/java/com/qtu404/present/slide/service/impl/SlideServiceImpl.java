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
    public int modifyContent(UserVo userVo, String content, String play, String slideId) {
        SlideVo slideVo = slideDao.fetchSlideById(slideId);
        if (slideVo.getUserId().equals(userVo.getUserId())) {//保证是自己的
            return slideDao.modifyContent(content, play, slideId);
        } else {
            return 0;
        }
    }

    @Override
    public int addNewSlide(String userId) {
        return slideDao.addNewSlide(userId);
    }

    @Override
    public SlideVo fetchSlideById(String slideId) {
        return slideDao.fetchSlideById(slideId);
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
