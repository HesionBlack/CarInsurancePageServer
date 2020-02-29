package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.*;
import org.javaboy.vhr.model.Car;

import java.util.Date;
import java.util.List;

/**
 * ClassName: CarinfoMapper <br/>
 * Description: <br/>
 * date: 2020/2/29 8:02<br/>
 *
 * @author Hesion<br />
 * @since JDK 1.8
 */
@Mapper
public interface CarinfoMapper {
    @Select({"<script> " +
            "SELECT * FROM car  WHERE del_flag = '0'" +
            "<if test=\"car.brand != null and car.brand != ''\">" +
            "AND brand like concat('%',#{car.brand},'%')</if>" +
            "<if test=\"car.model != null and car.model != ''\">" +
            "AND model like concat('%',#{car.model},'%')</if>" +
            "<if test=\"car.carmaster != null and car.carmaster != ''\">" +
            "AND carmaster like concat('%',#{car.carmaster},'%')</if>" +
            "<if test=\"page != null and size != null\">" +
            "limit #{page},#{size}</if>" +
            "</script>"})
    @Results(id = "CarinfoResult",
            value = {
                    @Result(property = "createTime", column = "create_time"),
                    @Result(property = "updateTime", column = "update_time"),
                    @Result(property = "createBy", column = "createby"),
                    @Result(property = "updateBy", column = "updateby"),
            })
    List<Car> getCarByPage(Integer page, Integer size,Car car,Date[] beginDateScope);


    @Delete("DELETE FROM car WHERE id = #{id}")
    int deleteCarByEid(String id);

    @Insert("INSERT INTO `car` VALUES(#{id},#{brand},#{model},#{address},#{telphone},#{carmaster},#{price},#{enginenum},#{createTime},#{createBy},#{updateTime},#{updateBy},\"0\")")
    int addCar(Car car);

    @Update({"<script> " +
            "UPDATE  car " +
            "<set> " +
            "<if test=\"brand != null\">" +
            "brand=#{brand},</if>" +
            "<if test=\"model != null\">" +
            "model=#{model},</if>" +
            "<if test=\"address != null\">" +
            "address=#{address},</if>" +
            "<if test=\"telphone != null\">" +
            "telphone=#{telphone},</if>" +
            "<if test=\"carmaster != null\">" +
            "carmaster=#{carmaster},</if>" +
            "<if test=\"price != null\">" +
            "price=#{price},</if>" +
            "<if test=\"enginenum != null\">" +
            "enginenum=#{enginenum},</if>" +
            "<if test=\"updateTime != null\">" +
            "update_time=#{updateTime},</if>" +
            "<if test=\"updateBy != null\">" +
            "updateby=#{updateBy},</if>" +
            "</set>" +
            "WHERE id=#{id}" +
            "</script>"})
    int updateByPrimaryKeySelective(Car car);

    @Select({"<script> " +
            "SELECT count(*) FROM car  where del_flag = '0'" +
            "<if test=\"car.brand != null\">" +
            "and brand=#{car.brand}</if>" +
            "<if test=\"car.model != null\">" +
            "and car.model=#{model}</if>" +
            "<if test=\"car.address != null\">" +
            "and address=#{car.address}</if>" +
            "<if test=\"car.telphone != null\">" +
            "and telphone=#{car.telphone}</if>" +
            "<if test=\"car.carmaster != null\">" +
            "and carmaster=#{car.carmaster}</if>" +
            "<if test=\"car.price != null\">" +
            "and price=#{car.price}</if>" +
            "<if test=\"car.enginenum != null\">" +
            "and enginenum=#{car.enginenum}</if>" +
            "<if test=\"car.updateTime != null\">" +
            "and update_time=#{car.updateTime}</if>" +
            "<if test=\"car.updateBy != null\">" +
            "and updateby=#{car.updateBy}</if>" +
            "</script>"})
    Long getTotal(Car car, Date[] beginDateScope);
}
