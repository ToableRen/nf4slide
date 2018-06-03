package com.qtu404.present.slide.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qtu404.present.slide.service.FileService;
import com.qtu404.present.slide.service.SlideService;
import com.qtu404.present.slide.vo.FileVo;
import com.qtu404.present.slide.vo.SlideVo;
import com.qtu404.present.user.vo.UserVo;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;


@Controller("imgAction")
@Scope("prototype")
@ParentPackage("json-default")
public class ImgAction extends ActionSupport {
    @Resource(name = "slideService")
    private SlideService slideService;

    @Resource(name = "fileService")
    private FileService fileService;

    private final String imgFileDateBase = "ImgFileDateBase";

    private final String pptSaveDirPath = "PPTXFileDateBase";

    private File file; //得到上传的文件
    private String type; //得到文件的类型
    private String name; //得到文件的名称
    private String result;//json response
    private String slideId;
    private String size;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Action(value = "pptUpLoad", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String slidePPTXUpLoad() {
        result = "fail";
        //上下文獲取
        ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();

        //参数获取以及封装
        String realPath = ServletActionContext.getServletContext().getRealPath("");
        UserVo loginUser = (UserVo) session.get("loginUser");
        FileVo fileVo = new FileVo();
        fileVo.setFileSaveDirPath(pptSaveDirPath);
        fileVo.setFile(file);
        fileVo.setSize(size);
        fileVo.setFileName(name);
        fileVo.setFileContentType(type);
        fileVo.setUserId(loginUser.getUserId());
        fileVo.setContextPath(realPath);

        //调用service保存文件
        String pptFilePath = fileService.saveFile(fileVo);
        SlideVo slideVo = fileService.savePPTToSlide(fileVo);
        try {
            result = objectMapper.writeValueAsString(slideVo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result = "fail";
        }
        return SUCCESS;
    }

    @Action(value = "slideImgUpLoad", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String slideImgUpLoad() {
        //上下文获取
        ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();

        //各项参数获取以及封装
        String realPath = ServletActionContext.getServletContext().getRealPath("");
        UserVo loginUser = (UserVo) session.get("loginUser");
        FileVo fileVo = new FileVo();
        fileVo.setFile(file);
        fileVo.setFileContentType(type);
        fileVo.setFileName(name);
        fileVo.setSize(size);
        fileVo.setFileSaveDirPath(imgFileDateBase);
        fileVo.setUserId(loginUser.getUserId());
        fileVo.setContextPath(realPath);

        result = fileService.saveFile(fileVo);
        return SUCCESS;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSlideId() {
        return slideId;
    }

    public void setSlideId(String slideId) {
        this.slideId = slideId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
