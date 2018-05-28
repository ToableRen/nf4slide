package com.qtu404.present.dao.impl;

import com.qtu404.present.dao.DBConfig;
import com.qtu404.present.domain.UserVo;

import java.sql.*;

public class UserDaoImpl {


    public static String addNewUser(UserVo user) {
        int rst = 0;

        String sql = "insert into account VALUES (?,?,?,?)";
        String userId = String.valueOf(ftechMaxId() + 1);
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
            stm.setString(2, user.getUsername());
            stm.setString(3, user.getPassword());
            stm.setString(4, user.getPhoneNum());

            System.out.println(stm);
            rst = stm.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }


        return userId;
    }

    public static int ftechMaxId() {
        int rst = 0;

        String sql = "SELECT max(account.userId) FROM account";

        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print(e);
        }

        try {
            PreparedStatement stm;
            Connection con;
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

    public static UserVo fetchUserByPhone(String phoneNum) {
        UserVo userVo = null;

        String sql = "SELECT * FROM account WHERE  account.phone = ? ";

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
            stm.setString(1, phoneNum);
            rs = stm.executeQuery();

            while (rs.next()) {
                userVo = new UserVo();
                userVo.setUserId(rs.getString("userId"));
                userVo.setUsername(rs.getString("username"));
                userVo.setPhoneNum(rs.getString("phone"));
            }

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return userVo;
    }

    public static UserVo fetchUserByLogin(String phoneNum, String password) {
        UserVo userVo = null;

        String sql = "SELECT * FROM account WHERE  account.phone = ? AND account.password = ?";

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
            stm.setString(1, phoneNum);
            stm.setString(2, password);
            rs = stm.executeQuery();

            while (rs.next()) {
                userVo = new UserVo();
                userVo.setUserId(rs.getString("userId"));
                userVo.setPhoneNum(rs.getString("phone"));
                userVo.setUsername(rs.getString("username"));
            }

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return userVo;
    }

    public static void main(String[] args) {
        UserVo userVo = fetchUserByPhone("10081");
        System.out.println(userVo);
    }
}
