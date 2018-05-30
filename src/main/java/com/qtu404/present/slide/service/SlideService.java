package com.qtu404.present.slide.service;

import com.qtu404.present.slide.vo.SlideVo;
import com.qtu404.present.user.vo.UserVo;

import java.util.ArrayList;

public interface SlideService {
    public int modifyContent(UserVo userVo, String content, String play, String slideId);

    /**
     * 返回被添加的slideid
     */
    public int addNewSlide(String userId);

    public SlideVo fetchSlideById(String slideId);

    public int delSlideById(UserVo userVo, String slideId);

    public int modifySlideName(UserVo userVo, String sildeId, String slideName);

    public ArrayList<SlideVo> findAllSlideByUserId(String userId);

}
