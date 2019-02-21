package org.sportbean.wx.sportbean.service.impl;

import java.util.List;

import org.sportbean.wx.sportbean.mapper.WxUserMapper;
import org.sportbean.wx.sportbean.pojo.EntryTablePojo;
import org.sportbean.wx.sportbean.pojo.WxUserPojo;
import org.sportbean.wx.sportbean.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    private WxUserMapper wxUserDao;
    // private WxUserInfoMapper wxUserInfoMapper;

    @Override
    public void insert(WxUserPojo wp) throws Exception {
        wxUserDao.insert(wp);
    }

    @Override
    public WxUserPojo getOneInfo(String openid) {
        return wxUserDao.getOneInfo(openid);
    }

    @Override
    public List<WxUserPojo> getAllInfo() {
        return wxUserDao.getAllInfo();
    }

    @Override
    public Boolean updateWxUserName(WxUserPojo record) {
        return wxUserDao.updateWxUserName(record);
    }

    @Override
    public Boolean entrySportEvent(EntryTablePojo record) throws Exception {
        return wxUserDao.entrySportEvent(record);
    };
}