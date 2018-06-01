package com.qtu404.present.slide.vo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SlideRowMapper implements RowMapper<SlideVo> {

    @Override
    public SlideVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        SlideVo slideVo = new SlideVo();
        slideVo.setContent(rs.getString("content"));
        slideVo.setName(rs.getString("name"));
        slideVo.setPlay(rs.getString("play"));
        slideVo.setSlideId(rs.getString("slideId"));
        slideVo.setUserId(rs.getString("userId"));
        slideVo.setTheme(rs.getString("theme"));
        slideVo.setConfig(rs.getString("config"));
        return slideVo;
    }
}
