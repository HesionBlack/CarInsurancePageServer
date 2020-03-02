package org.javaboy.vhr.model;
/**
 * ClassName: InsList <br/>
 * Description: <br/>
 * date: 2020/3/2 20:18<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

/**
 * @program: vhr
 * @description:
 * @author: hesion
 * @create: 2020-03-02 20:18
 **/
public class InsList {
    private String value;
    private String lable;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "InsList{" +
                "value='" + value + '\'' +
                ", lable='" + lable + '\'' +
                '}';
    }
}
