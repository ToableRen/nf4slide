package com.qtu404.present.slide.service.impl;

import com.qtu404.obsolete.file.UploadSlideUnit;
import com.qtu404.present.slide.service.FileService;
import com.qtu404.present.slide.vo.FileVo;
import com.qtu404.present.slide.vo.SlideVo;
import com.qtu404.present.user.vo.UserVo;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Override
    public SlideVo savePPTToSlide(FileVo fileVo) {
        SlideVo slideVo = new SlideVo();

        ArrayList<String> list = new ArrayList<>();
        String realSaveDirPath = fileVo.getContextPath() + fileVo.getFileSaveDirPath() + "/" + fileVo.getUserId();
        boolean isSave = UploadSlideUnit.doPPT2007toImage(new File(fileVo.getRealPath()), new File(realSaveDirPath), list, fileVo.getFileName());

        return slideVo;
    }

    @Override
    public String saveFile(FileVo fileVo) {
        String result = null;
        //获取要保存文件夹的物理路径(绝对路径)
        String realPath = fileVo.getContextPath() + "/" + fileVo.getFileSaveDirPath();
        realPath = realPath + "/" + fileVo.getUserId();
        File saveFile = new File(realPath);
        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        if (!saveFile.exists()) saveFile.mkdirs();
        try {
            //保存文件
            FileUtils.copyFile(fileVo.getFile(), new File(saveFile, fileVo.getFileName()));
            result = fileVo.getFileSavePath();
        } catch (IOException e) {
            result = "upLoadImgFail";
            e.printStackTrace();
        }
        return result;
    }
}
