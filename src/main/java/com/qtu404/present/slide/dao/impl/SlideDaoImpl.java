package com.qtu404.present.slide.dao.impl;

import com.qtu404.present.slide.vo.SlideRowMapper;
import com.qtu404.present.slide.vo.SlideVo;
import com.qtu404.present.slide.dao.SlideDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("slideDao")
public class SlideDaoImpl implements SlideDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(com.mchange.v2.c3p0.ComboPooledDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private static final String DEFAULTNAME = "new slide";

    @Override
    public int modifyContent(final SlideVo slideVo) {
        int rst = 0;
        String sql = "UPDATE slides SET  content = ?,play = ? ,config = ?,theme = ? WHERE  slideId = ?";
        rst = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, slideVo.getContent());
                ps.setString(2, slideVo.getPlay());
                ps.setString(3, slideVo.getConfig());
                ps.setString(4, slideVo.getTheme());
                ps.setString(5, slideVo.getSlideId());
            }
        });
        return rst;
    }

    /**
     * 返回被添加的slideid
     */
    @Override
    public int addNewSlide(final String userId) {

        String sql = "insert into slides VALUES (?,?,?,'','','true','','league')";
        final int slide_int = fetchMaxSlideId() + 1;
        final String slideId = String.valueOf(slide_int);
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, userId);
                ps.setString(2, slideId);
                ps.setString(3, DEFAULTNAME);
            }
        });
        return slide_int;
    }

    public int fetchMaxSlideId() {
        String sql = "SELECT max(slideId) FROM slides";
        Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
        if (maxId == null) {
            maxId = 100000;
        }
        return maxId;
    }

    @Override
    public SlideVo fetchSlideById(String slideId) {
        SlideVo slideVo = null;
        String sql = "SELECT * FROM slides WHERE  slideId = ?";
        Object[] args = {slideId};
        int[] ProType = {Types.VARCHAR};
        slideVo = jdbcTemplate.queryForObject(sql, args, ProType, new SlideRowMapper());
        return slideVo;
    }

    @Override
    public int delSlideById(final String slideId) {
        int rst = 0;
        String sql = "UPDATE slides SET slides.exit = 'false'  WHERE  slideId = ?";
        rst = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, slideId);
            }
        });
        return rst;
    }

    @Override
    public int modifySlideName(final String sildeId, final String slideName) {
        int rst = 0;
        String sql = "UPDATE slides SET name = ? WHERE  slideId = ?";
        rst = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, slideName);
                ps.setString(2, sildeId);
            }
        });
        return rst;
    }

    @Override
    public ArrayList<SlideVo> findAllSlideByUserId(String userId) {
        ArrayList<SlideVo> slideVos = null;
        String sql = "SELECT slides.userId,slides.name,slides.slideId FROM slides " +
                "WHERE  userId = ? AND slides.exit = ? ORDER BY slideId DESC ";

        Object[] args = {userId, "true"};
        int[] argTypes = {Types.VARCHAR, Types.VARCHAR};

        slideVos = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor<ArrayList<SlideVo>>() {
            @Override
            public ArrayList<SlideVo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                ArrayList<SlideVo> rst = new ArrayList<>();
                while (rs.next()) {
                    SlideVo slideVo = new SlideVo();
                    slideVo.setUserId(rs.getString("userId"));
                    slideVo.setSlideId(rs.getString("slideId"));
                    slideVo.setName(rs.getString("name"));
                    rst.add(slideVo);
                }
                return rst;
            }
        });

        return slideVos;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SlideDao slideDao = (SlideDao) ctx.getBean("slideDao");
        SlideVo slideVo = slideDao.fetchSlideById("100003");
        System.out.println("11");
    }
}