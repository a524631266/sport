package org.sportbean.wx.sportbean.pojo;

import java.io.Serializable;

public class AdminPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer aid;
    private Integer level;
    private Integer rid;
    private String name;
    private String password;

    public AdminPojo() {
    }

    public AdminPojo(Integer aid, Integer level, Integer rid, String name, String password) {
        this.aid = aid;
        this.level = level;
        this.rid = rid;
        this.name = name;
        this.password = password;
    }

    public Integer getAid() {
        return this.aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRid() {
        return this.rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" + " aid='" + getAid() + "'" + ", level='" + getLevel() + "'" + ", rid='" + getRid() + "'"
                + ", name='" + getName() + "'" + ", password='" + getPassword() + "'" + "}";
    }

}