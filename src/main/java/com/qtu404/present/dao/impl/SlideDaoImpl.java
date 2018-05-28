package com.qtu404.present.dao.impl;

import com.qtu404.present.dao.DBConfig;
import com.qtu404.present.domain.SlideVo;

import java.sql.*;
import java.util.ArrayList;

public class SlideDaoImpl {

    private static final String DEFAULTNAME = "new slide";

    public static int modifyContent(String content, String play, String slideId) {
        int rst = 0;

        String sql = "UPDATE slides SET content = ?,play = ? WHERE  slideId = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print(e);
        }

        try {
            Connection con;
            PreparedStatement stm;
            con = DriverManager.getConnection(DBConfig.url, DBConfig.id, DBConfig.password);
            stm = con.prepareStatement(sql);
            stm.setString(1, content);
            stm.setString(2, play);
            stm.setString(3, slideId);
            rst = stm.executeUpdate();


            con.close();
        } catch (SQLException e) {
        }
        return rst;
    }

    /**
     * 返回被添加的slideid
     */
    public static int addNewSlide(String userId) {
        int rst = -1;

        String sql = "insert into slides VALUES (?,?,?,'','','true')";
        int slide_int = getMaxSlideId() + 1;
        String slideId = String.valueOf(slide_int);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print(e);
        }


        try {
            Connection con;
            PreparedStatement stm;
            con = DriverManager.getConnection(DBConfig.url, DBConfig.id, DBConfig.password);
            stm = con.prepareStatement(sql);
            stm.setString(1, userId);
            stm.setString(2, slideId);
            stm.setString(3, DEFAULTNAME);
            System.out.println(stm);
            rst = stm.executeUpdate();
            rst = slide_int;
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }


        return rst;
    }

    public static int getMaxSlideId() {
        int rst = 0;

        String sql = "SELECT max(slideId) FROM slides";

        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print(e);
        }

        try {
            Connection con;
            PreparedStatement stm;
            con = DriverManager.getConnection(DBConfig.url, DBConfig.id, DBConfig.password);
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                rst = rs.getInt(1);
            }

            if (rst == 0) {
                rst = 100001;
            }

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }


        return rst;
    }

    public static SlideVo fetchSlideById(String slideId) {
        SlideVo slideVo = null;

        String sql = "SELECT * FROM slides WHERE  slideId = ?";

        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print(e);
        }

        try {
            Connection con;
            PreparedStatement stm;
            con = DriverManager.getConnection(DBConfig.url, DBConfig.id, DBConfig.password);
            stm = con.prepareStatement(sql);
            stm.setString(1, slideId);
            rs = stm.executeQuery();

            while (rs.next()) {
                slideVo = new SlideVo();
                slideVo.setSlideId(rs.getString("slideId"));
                slideVo.setUserId(rs.getString("userId"));
                slideVo.setName(rs.getString("name"));
                slideVo.setContent(rs.getString("content"));
                slideVo.setPlay(rs.getString("play"));
            }

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return slideVo;
    }

    public static int delSlideById(String slideId) {

        int rst = 0;

        String sql = "UPDATE slides SET slides.exit = 'false'  WHERE  slideId = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print(e);
        }

        try {
            Connection con;
            PreparedStatement stm;
            con = DriverManager.getConnection(DBConfig.url, DBConfig.id, DBConfig.password);
            stm = con.prepareStatement(sql);
            stm.setString(1, slideId);
            rst = stm.executeUpdate();

            con.close();
        } catch (SQLException e) {
        }
        return rst;
    }

    public static int modifySlideName(String sildeId, String slideName) {
        int rst = 0;

        String sql = "UPDATE slides SET name = ? WHERE  slideId = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print(e);
        }

        try {
            Connection con;
            PreparedStatement stm;
            con = DriverManager.getConnection(DBConfig.url, DBConfig.id, DBConfig.password);
            stm = con.prepareStatement(sql);
            stm.setString(1, slideName);
            stm.setString(2, sildeId);
            rst = stm.executeUpdate();

            con.close();
        } catch (SQLException e) {
        }
        return rst;
    }

    public static ArrayList<SlideVo> findAllSlideByUserId(String userId) {
        ArrayList<SlideVo> slideVos = new ArrayList<>();


        String sql = "SELECT * FROM slides WHERE  userId = ? AND slides.exit = 'true' ORDER BY slideId DESC ";

        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print(e);
        }

        try {
            Connection con;
            PreparedStatement stm;
            con = DriverManager.getConnection(DBConfig.url, DBConfig.id, DBConfig.password);
            stm = con.prepareStatement(sql);
            stm.setString(1, userId);
            rs = stm.executeQuery();

            while (rs.next()) {
                SlideVo slideVo = new SlideVo();
                slideVo = new SlideVo();
                slideVo.setSlideId(rs.getString("slideId"));
                slideVo.setUserId(rs.getString("userId"));
                slideVo.setName(rs.getString("name"));
                slideVos.add(slideVo);
            }

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }


        return slideVos;
    }

    public static void main(String[] args) {

        findAllSlideByUserId("100001");

    }
}