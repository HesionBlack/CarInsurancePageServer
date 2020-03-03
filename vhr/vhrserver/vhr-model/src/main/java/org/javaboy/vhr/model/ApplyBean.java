package org.javaboy.vhr.model;
/**
 * ClassName: ApplyBean <br/>
 * Description: <br/>
 * date: 2020/3/3 21:17<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

/**
 * @program: vhr
 * @description:
 * @author: hesion
 * @create: 2020-03-03 21:17
 **/
public class ApplyBean {
    private String id;
    private String insId;
    private String carId;
    private Integer duration;
    private Double finalPrice;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public String toString() {
        return "ApplyBean{" +
                "id='" + id + '\'' +
                ", insId='" + insId + '\'' +
                ", carId='" + carId + '\'' +
                ", duration=" + duration +
                ", finalPrice=" + finalPrice +
                '}';
    }
}
