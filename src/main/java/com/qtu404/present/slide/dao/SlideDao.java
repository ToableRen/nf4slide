package com.qtu404.present.slide.dao;

import com.qtu404.present.slide.vo.SlideVo;

import java.util.ArrayList;

public interface SlideDao {

    public int modifyContent(String content, String play, String slideId);

    /**
     * 返回被添加的slideid
     */
    public int addNewSlide(String userId);

    public SlideVo fetchSlideById(String slideId);

    public int delSlideById(String slideId);

    public int modifySlideName(String sildeId, String slideName);

    public ArrayList<SlideVo> findAllSlideByUserId(String userId);

}
