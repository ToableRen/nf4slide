package com.qtu404.present.user.dao.impl;

import com.qtu404.present.user.dao.UserDao;
import com.qtu404.present.unit.DBConfig;
import com.qtu404.present.user.vo.UserRowMapper;
import com.qtu404.present.user.vo.UserVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(com.mchange.v2.c3p0.ComboPooledDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String addNewUser(final UserVo user) {

        String sql = "insert into account VALUES (?,?,?,?)";
        final String userId = String.valueOf(ftechMaxId() + 1);
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, userId);
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getPhoneNum());
            }
        });
        return userId;
    }

    public int ftechMaxId() {

        String sql = "SELECT max(account.userId) FROM account";
        //        调用方法获得记录数
        Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
        if (maxId == null) {
            maxId = 100000;
        }
        return maxId;
    }

    public UserVo fetchUserByPhone(String phoneNum) {
        UserVo userVo = new UserVo();
        String sql = "SELECT * FROM account WHERE  " +
                "account.phone = ?";

        Object[] args = {phoneNum};
        int[] ProType = {Types.VARCHAR};

        userVo = jdbcTemplate.queryForObject(sql, args, ProType, new UserRowMapper());
        return userVo;
    }

    public UserVo fetchUserByLogin(String phoneNum, String password) {
        UserVo userVo = new UserVo();
        String sql = "SELECT * FROM account WHERE  " +
                "account.phone = ? AND account.password = ?";

        Object[] args = {phoneNum, password};
        int[] ProType = {Types.VARCHAR, Types.VARCHAR};

        userVo = jdbcTemplate.queryForObject(sql, args, ProType, new UserRowMapper());
        return userVo;
    }

}
