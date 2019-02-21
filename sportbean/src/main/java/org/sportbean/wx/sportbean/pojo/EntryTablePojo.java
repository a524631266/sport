package org.sportbean.wx.sportbean.pojo;

import java.io.Serializable;

public class EntryTablePojo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer uid;
    private Integer sid;

    public EntryTablePojo() {
    }

    public EntryTablePojo(Integer uid, Integer sid) {
        this.uid = uid;
        this.sid = sid;
    }

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSid() {
        return this.sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "{" + " uid='" + getUid() + "'" + ", sid='" + getSid() + "'" + "}";
    }

}