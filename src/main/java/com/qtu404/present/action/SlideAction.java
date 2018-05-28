package com.qtu404.present.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qtu404.present.dao.impl.SlideDaoImpl;
import com.qtu404.present.domain.SlideVo;
import com.qtu404.present.domain.UserVo;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.util.ArrayList;
import java.util.Map;

@ParentPackage("json-default")
public class SlideAction extends ActionSupport {
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
        ArrayList<SlideVo> slideVos = new SlideDaoImpl().findAllSlideByUserId(loginUser.getUserId());
        result = objectMapper.writeValueAsString(slideVos);
        return SUCCESS;
    }

    @Action(value = "fetchSlideContentById", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String fetchSlideContentById() {
        SlideVo slideVo = SlideDaoImpl.fetchSlideById(slideId);
        result = slideVo.getContent();
        return SUCCESS;
    }

    @Action(value = "fetchSlidePlayById", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String fetchSlidePlayById() {
        SlideVo slideVo = SlideDaoImpl.fetchSlideById(slideId);
        result = slideVo.getPlay();
        return SUCCESS;
    }

    @Action(value = "addNewSlide", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String addNewSlide() {
        Map session = ActionContext.getContext().getSession();
        UserVo userVo = (UserVo) session.get("loginUser");
        int rst = SlideDaoImpl.addNewSlide(userVo.getUserId());
        result = String.valueOf(rst);
        return SUCCESS;
    }

    @Action(value = "modifySlideName", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String modifySlideName() {
        Map session = ActionContext.getContext().getSession();
        UserVo userVo = (UserVo) session.get("loginUser");
        SlideVo slideVo = SlideDaoImpl.fetchSlideById(slideId);

        //保证是自己的
        if (slideVo.getUserId().equals(userVo.getUserId())) {
            SlideDaoImpl.modifySlideName(slideId, name);
        }

        return SUCCESS;
    }

    @Action(value = "delSlide", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String delSilde() {
        Map session = ActionContext.getContext().getSession();
        UserVo userVo = (UserVo) session.get("loginUser");
        SlideVo slideVo = SlideDaoImpl.fetchSlideById(slideId);

        if (slideVo.getUserId().equals(userVo.getUserId())) {//保证是自己的
            int rst = SlideDaoImpl.delSlideById(slideId);
            if (rst == 1) {
                result = "delSuccess";
            } else {
                result = "delFail";
            }
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
        SlideVo slideVo = SlideDaoImpl.fetchSlideById(slideId);

        if (slideVo.getUserId().equals(userVo.getUserId())) {//保证是自己的

            int rst = SlideDaoImpl.modifyContent(content, play, slideId);
            if (rst == 1) {
                result = "modifySuccess";
            } else {
                result = "modifyFail";
            }
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
}