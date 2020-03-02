package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (VAccpetView)实体类
 *
 * @author makejava
 * @since 2020-03-02 13:32:36
 */
public class VAccpetView implements Serializable {
    private static final long serialVersionUID = -11129623807251545L;

    private String id;
    private String carId;
    private String insId;

    private String carbrand;

    private String carmaster;

    private String name;

    private Double finalprice;
    private Integer duration;
    private Integer accept;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    private String createBy;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

    private String updateBy;

    private String del_flag;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
    }

    public Double getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(Double finalprice) {
        this.finalprice = finalprice;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(String carbrand) {
        this.carbrand = carbrand;
    }

    public String getCarmaster() {
        return carmaster;
    }

    public void setCarmaster(String carmaster) {
        this.carmaster = carmaster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}