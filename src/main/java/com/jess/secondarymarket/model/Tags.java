package com.jess.secondarymarket.model;

import java.io.Serializable;

public class Tags implements Serializable {
    private Integer id;

    private String tagsName;

    private String tagsMsg;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName == null ? null : tagsName.trim();
    }

    public String getTagsMsg() {
        return tagsMsg;
    }

    public void setTagsMsg(String tagsMsg) {
        this.tagsMsg = tagsMsg == null ? null : tagsMsg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tagsName=").append(tagsName);
        sb.append(", tagsMsg=").append(tagsMsg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}