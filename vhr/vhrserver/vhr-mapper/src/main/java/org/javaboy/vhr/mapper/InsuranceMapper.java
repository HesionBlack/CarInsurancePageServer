package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.*;
import org.javaboy.vhr.model.Insurance;

import java.util.Date;
import java.util.List;

/**
 * ClassName: InsuranceMapper <br/>
 * Description: <br/>
 * date: 2020/3/1 10:16<br/>
 *
 * @author Hesion<br />
 * @since JDK 1.8
 */
@Mapper
public interface InsuranceMapper {
    @Select({"<script> " +
            "SELECT * FROM insurance  WHERE del_flag = '0'" +
            "<if test=\"ins.name != null and ins.name != ''\">" +
            "AND `name` like concat('%',#{ins.name},'%')</if>" +
            "<if test=\"page != null and size != null\">" +
            "limit #{page},#{size}</if>" +
            "</script>"})
    @Results(id = "InsinfoResult",
            value = {
                    @Result(property = "createTime", column = "create_time"),
                    @Result(property = "updateTime", column = "update_time"),
                    @Result(property = "createBy", column = "createby"),
                    @Result(property = "updateBy", column = "updateby"),
            })
    List<Insurance> getInsuranceByPage(Integer page, Integer size, Insurance ins, Date[] beginDateScope);

    @Select({"<script> " +
            "SELECT count(*) FROM insurance  where del_flag = '0'" +
            "<if test=\"insurance.name != null\">" +
            "and name=#{insurance.name}</if>" +
            "</script>"})
    Long getTotal(Insurance insurance, Date[] beginDateScope);

    @Delete("DELETE FROM insurance WHERE id = #{id}")
    int deleteInsByEid(String id);

    @Insert("INSERT INTO insurance VALUES(#{id},#{name},#{desc},#{price},#{createTime},#{createBy},#{updateTime},#{updateBy},\"0\"")
    int addIns(Insurance ins);
    @Update({"<script> " +
            "UPDATE  insurance " +
            "<set> " +
            "<if test=\"name != null\">" +
            "`name`=#{name},</if>" +
            "<if test=\"desc != null\">" +
            "`desc`=#{desc},</if>" +
            "<if test=\"price != null\">" +
            "`price`=#{price},</if>" +
            "<if test=\"updateTime != null\">" +
            "`update_time`=#{updateTime},</if>" +
            "<if test=\"updateBy != null\">" +
            "`updateby`=#{updateBy},</if>" +
            "</set>" +
            "WHERE id=#{id}" +
            "</script>"})
    int updateIns(Insurance ins);
}
