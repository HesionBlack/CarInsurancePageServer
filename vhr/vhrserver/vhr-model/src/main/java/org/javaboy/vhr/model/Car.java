package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Car)实体类
 *
 * @author makejava
 * @since 2020-02-29 07:48:02
 */
public class Car implements Serializable {
    private static final long serialVersionUID = -24748841169011840L;

    private String id;

    private String brand;

    private String model;

    private String address;

    private Integer telphone;

    private String carmaster;

    private Double price;

    private String enginenum;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "Asia/Shanghai")
    private Date createTime;
    private String createBy;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "Asia/Shanghai")
    private Date updateTime;
    private String updateBy;
    private Integer outdanger;

    public Integer getOutdanger() {
        return outdanger;
    }

    public void setOutdanger(Integer outdanger) {
        this.outdanger = outdanger;
    }

    private String del_flag;

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTelphone() {
        return telphone;
    }

    public void setTelphone(Integer telphone) {
        this.telphone = telphone;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEnginenum() {
        return enginenum;
    }

    public void setEnginenum(String enginenum) {
        this.enginenum = enginenum;
    }

    public String getCarmaster() {
        return carmaster;
    }

    public void setCarmaster(String carmaster) {
        this.carmaster = carmaster;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", address='" + address + '\'' +
                ", telphone=" + telphone +
                ", carmaster='" + carmaster + '\'' +
                ", price=" + price +
                ", enginenum='" + enginenum + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", outdanger=" + outdanger +
                ", del_flag='" + del_flag + '\'' +
                '}';
    }
}