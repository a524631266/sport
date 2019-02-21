package org.sportbean.wx.sportbean.pojo;

import java.io.Serializable;

// import com.sportbean.wx.common.Sex;

public class WxUserPojo implements Serializable { // implements Serializable
    private static final long serialVersionUID = 1L;
    private Integer uid; // uid
    private String name; // 姓名
    private String phone; // 手机号
    private String cid; // 身份证
    private Integer sex; // 0 女 1 男
    private Integer rid; // 所选择区域
    private String openid; // openid 用来唯一标志用户

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getRid() {
        return this.rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "{" + " uid='" + getUid() + "'" + ", name='" + getName() + "'" + ", phone='" + getPhone() + "'"
                + ", cid='" + getCid() + "'" + ", sex='" + getSex() + "'" + ", rid='" + getRid() + "'" + ", openid='"
                + getOpenid() + "'" + "}";
    }

}