package org.sportbean.wx.sportbean.pojo;

import java.io.Serializable;

public class RegionPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer rid; // 区域id 自增
    private String name; // 姓名
    private Integer pid; // 所选择区域
    private Integer level; // 等级

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

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}