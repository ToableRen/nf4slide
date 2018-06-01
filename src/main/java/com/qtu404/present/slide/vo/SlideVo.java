package com.qtu404.present.slide.vo;

public class SlideVo {
    public SlideVo() {
    }

    private String slideId;
    private String userId;
    private String name;
    private String content;
    private String play;
    private String config;
    private String theme;
    private String whoPlay;

    public String getWhoPlay() {
        return whoPlay;
    }

    public void setWhoPlay(String whoPlay) {
        this.whoPlay = whoPlay;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getSlideId() {
        return slideId;
    }

    public void setSlideId(String slideId) {
        this.slideId = slideId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
