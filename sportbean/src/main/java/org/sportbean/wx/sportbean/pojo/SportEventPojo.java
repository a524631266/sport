package org.sportbean.wx.sportbean.pojo;

import java.io.Serializable;

public class SportEventPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer sid; // uid
    private String title; // 姓名
    private String content; // 手机号
    private String cid; // 身份证
    private Integer rid; // 所选择区域
    private Integer starttime; // 1313464545 时间戳
    private Integer expiretime;

    public Integer getSid() {
        return this.sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getRid() {
        return this.rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getStarttime() {
        return this.starttime;
    }

    public void setStarttime(Integer starttime) {
        this.starttime = starttime;
    }

    public Integer getExpiretime() {
        return this.expiretime;
    }

    public void setExpiretime(Integer expiretime) {
        this.expiretime = expiretime;
    }

}