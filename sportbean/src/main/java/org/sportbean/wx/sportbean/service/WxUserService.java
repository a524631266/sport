package org.sportbean.wx.sportbean.service;

import java.util.List;

import org.sportbean.wx.sportbean.pojo.EntryTablePojo;
import org.sportbean.wx.sportbean.pojo.WxUserPojo;

public interface WxUserService {
    public void insert(WxUserPojo wp) throws Exception;

    public WxUserPojo getOneInfo(String openid);

    public List<WxUserPojo> getAllInfo();

    public Boolean updateWxUserName(WxUserPojo record);

    public Boolean entrySportEvent(EntryTablePojo record) throws Exception;
}