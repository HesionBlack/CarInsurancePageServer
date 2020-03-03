package org.javaboy.vhr.model;

import java.util.Date;
import java.io.Serializable;

/**
 * (Acceptins)实体类
 *
 * @author makejava
 * @since 2020-03-01 15:58:35
 */
public class Acceptins implements Serializable {
    private static final long serialVersionUID = 147957832147342290L;
    
    private String id;
    
    private String carId;
    
    private String insId;
    
    private Double finalprice;
    
    private Integer accept;
    private Integer duration;
    
    private Date createTime;
    
    private String createBy;
    
    private Date updateTime;
    
    private String updateBy;
    
    private String del_flag;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId;
    }

    public Double getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(Double finalprice) {
        this.finalprice = finalprice;
    }

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }
}