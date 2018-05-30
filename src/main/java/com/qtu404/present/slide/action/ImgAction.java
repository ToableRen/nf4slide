package com.qtu404.present.slide.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qtu404.present.user.vo.UserVo;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@Controller("imgAction")
@Scope("prototype")
@ParentPackage("json-default")
public class ImgAction extends ActionSupport {
    private File file; //得到上传的文件
    private String type; //得到文件的类型
    private String name; //得到文件的名称
    private String result;//json response
    private String slideId;

    @Action(value = "slideImgUpLoad", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String slideImgUpLoad() {
        //当前登录的人
        ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        UserVo loginUser = (UserVo) session.get("loginUser");


        //获取要保存文件夹的物理路径(绝对路径)
        String realPath = ServletActionContext.getServletContext().getRealPath("/ImgFileDateBase");
        realPath = realPath + "/" + loginUser.getUserId();
        File saveFile = new File(realPath);

        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        if (!saveFile.exists()) saveFile.mkdirs();

        try {
            //保存文件
            FileUtils.copyFile(file, new File(saveFile, name));//"/" + projectName + "/
            result = "ImgFileDateBase/" + loginUser.getUserId() + "/" + name;
        } catch (IOException e) {
            result = "upLoadImgFail";
            e.printStackTrace();
        }
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
}
