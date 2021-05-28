package com.jess.secondarymarket.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable {
    private Long id;

    private Integer userId;

    private Integer tagsId;

    private String goodsName;

    private BigDecimal goodsPrice;

    private Integer goodsNum;

    private String goodsLevel;

    private String goodsDue;

    private Long goodsRead;

    private Long goodsLike;

    private Integer goodsStatus;

    private String goodsComment;

    private String createTime;

    private String updateTime;

    private String goodsIntro;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTagsId() {
        return tagsId;
    }

    public void setTagsId(Integer tagsId) {
        this.tagsId = tagsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsLevel() {
        return goodsLevel;
    }

    public void setGoodsLevel(String goodsLevel) {
        this.goodsLevel = goodsLevel == null ? null : goodsLevel.trim();
    }

    public String getGoodsDue() {
        return goodsDue;
    }

    public void setGoodsDue(String goodsDue) {
        this.goodsDue = goodsDue == null ? null : goodsDue.trim();
    }

    public Long getGoodsRead() {
        return goodsRead;
    }

    public void setGoodsRead(Long goodsRead) {
        this.goodsRead = goodsRead;
    }

    public Long getGoodsLike() {
        return goodsLike;
    }

    public void setGoodsLike(Long goodsLike) {
        this.goodsLike = goodsLike;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsComment() {
        return goodsComment;
    }

    public void setGoodsComment(String goodsComment) {
        this.goodsComment = goodsComment == null ? null : goodsComment.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getGoodsIntro() {
        return goodsIntro;
    }

    public void setGoodsIntro(String goodsIntro) {
        this.goodsIntro = goodsIntro == null ? null : goodsIntro.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", tagsId=").append(tagsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", goodsNum=").append(goodsNum);
        sb.append(", goodsLevel=").append(goodsLevel);
        sb.append(", goodsDue=").append(goodsDue);
        sb.append(", goodsRead=").append(goodsRead);
        sb.append(", goodsLike=").append(goodsLike);
        sb.append(", goodsStatus=").append(goodsStatus);
        sb.append(", goodsComment=").append(goodsComment);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", goodsIntro=").append(goodsIntro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}