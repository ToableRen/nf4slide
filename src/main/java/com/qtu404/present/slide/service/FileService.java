package com.qtu404.present.slide.service;

import com.qtu404.present.slide.vo.FileVo;
import com.qtu404.present.slide.vo.SlideVo;
import com.qtu404.present.user.vo.UserVo;

public interface FileService {
    public String saveFile(FileVo file);

    public SlideVo savePPTToSlide(FileVo fileVo);
}
