package com.qtu404.present.log.dao.impl;

import com.qtu404.present.log.dao.LogDao;
import com.qtu404.present.unit.DBConfig;
import com.qtu404.present.log.vo.LogVo;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("logDao")
public class LogDaoImpl implements LogDao {
    public int addNewLog(LogVo logVo) {
        int rst = 0;

        String sql = "INSERT INTO log VALUES (?,?,?,?,?,?)";

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
            stm.setInt(1, fetchMaxId() + 1);
            stm.setString(2, logVo.getUserId());
            stm.setString(3, logVo.getIpAddress());
            stm.setString(4, logVo.getDate().toString());
            stm.setString(5, logVo.getOperator());
            stm.setString(6, logVo.getParameter());
            rst = stm.executeUpdate();


            con.close();
        } catch (SQLException e) {
        }
        return rst;
    }

    public int fetchMaxId() {
        int rst = 0;

        String sql = "SELECT MAX(id) FROM log";
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print(e);
        }

        try {
            Connection con;
            con = DriverManager.getConnection(DBConfig.url, DBConfig.id, DBConfig.password);

            PreparedStatement stm;
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                rst = rs.getInt(1);
            }

            if (rst == 0) {
                rst = 100000;
            }

            con.close();
        } catch (SQLException e) {
        }
        return rst;
    }
}
