package com.qtu404.present.slide.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qtu404.present.slide.dao.impl.SlideDaoImpl;
import com.qtu404.present.slide.service.SlideService;
import com.qtu404.present.slide.vo.SlideVo;
import com.qtu404.present.user.vo.UserVo;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@Controller("slideAciton")
@Scope("prototype")
@ParentPackage("json-default")
public class SlideAction extends ActionSupport {
    @Resource(name = "slideService")
    private SlideService slideService;

    private String slideId;
    private String content;
    private String play;
    private String name;
    private String result;
    private ObjectMapper objectMapper = new ObjectMapper();



    @Action(value = "findAllSlideByLogin", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String findAllSlideByLogin() throws JsonProcessingException {
        ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        UserVo loginUser = (UserVo) session.get("loginUser");
        ArrayList<SlideVo> slideVos = slideService.findAllSlideByUserId(loginUser.getUserId());
        result = objectMapper.writeValueAsString(slideVos);
        return SUCCESS;
    }

    @Action(value = "fetchSlideContentById", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String fetchSlideContentById() {
        SlideVo slideVo = slideService.fetchSlideById(slideId);
        result = slideVo.getContent();
        return SUCCESS;
    }

    @Action(value = "fetchSlidePlayById", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String fetchSlidePlayById() {
        SlideVo slideVo = slideService.fetchSlideById(slideId);
        result = slideVo.getPlay();
        return SUCCESS;
    }

    @Action(value = "addNewSlide", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String addNewSlide() {
        Map session = ActionContext.getContext().getSession();
        UserVo userVo = (UserVo) session.get("loginUser");
        int rst = slideService.addNewSlide(userVo.getUserId());
        result = String.valueOf(rst);
        return SUCCESS;
    }

    @Action(value = "modifySlideName", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String modifySlideName() {
        Map session = ActionContext.getContext().getSession();
        UserVo userVo = (UserVo) session.get("loginUser");
        slideService.modifySlideName(userVo, slideId, name);
        return SUCCESS;
    }

    @Action(value = "delSlide", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String delSilde() {
        Map session = ActionContext.getContext().getSession();
        UserVo userVo = (UserVo) session.get("loginUser");
        int rst = slideService.delSlideById(userVo, slideId);
        if (rst == 1) {
            result = "delSuccess";
        } else {
            result = "delFail";
        }
        return SUCCESS;
    }

    @Action(value = "modifySlideContent", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String modifySlideContent() {
        Map session = ActionContext.getContext().getSession();
        UserVo userVo = (UserVo) session.get("loginUser");
        int rst = slideService.modifyContent(userVo, content, play, slideId);
        if (rst == 1) {
            result = "modifySuccess";
        } else {
            result = "modifyFail";
        }
        return SUCCESS;
    }

    public String getSlideId() {
        return slideId;
    }

    public void setSlideId(String slideId) {
        this.slideId = slideId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
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

    public SlideService getSlideService() {
        return slideService;
    }

    public void setSlideService(SlideService slideService) {
        this.slideService = slideService;
    }
}