package org.sportbean.wx.sportbean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.sportbean.wx.sportbean.pojo.EntryTablePojo;
import org.sportbean.wx.sportbean.pojo.WxUserPojo;

public interface WxUserMapper {
    @Insert("insert into wxuser(name,phone,cid,sex,rid,openid) values(#{name},#{phone},#{cid},#{sex},#{rid},#{openid})")
    public int insert(WxUserPojo record) throws Exception;

    @Select("SELECT * FROM wxuser WHERE openid=${openid}")
    public WxUserPojo getOneInfo(@Param("openid") String openid);

    @Select("select * from wxuser")
    public List<WxUserPojo> getAllInfo();

    @Update("update wxuser set name=#{name} where openid=#{openid}")
    public Boolean updateWxUserName(WxUserPojo record);

    @Insert("insert into entrytable(uid,sid) values(#{uid},#{sid})")
    public Boolean entrySportEvent(EntryTablePojo record) throws Exception;
}