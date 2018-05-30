package com.qtu404.present.log.service.impl;

import com.qtu404.present.log.dao.LogDao;
import com.qtu404.present.log.vo.LogVo;

public class LogServiceImpl implements LogDao {
    LogDao logDao;

    @Override
    public int addNewLog(LogVo logVo) {
        return logDao.addNewLog(logVo);
    }
}
