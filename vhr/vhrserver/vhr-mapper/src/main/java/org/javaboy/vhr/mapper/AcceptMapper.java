package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.*;
import org.javaboy.vhr.model.Acceptins;
import org.javaboy.vhr.model.Car;
import org.javaboy.vhr.model.VAccpetView;

import java.util.Date;
import java.util.List;

/**
 * ClassName: AcceptMapper <br/>
 * Description: <br/>
 * date: 2020/3/2 13:35<br/>
 *
 * @author Hesion<br />
 * @since JDK 1.8
 */
@Mapper
public interface AcceptMapper {
    @Select({"<script> " +
            "SELECT * FROM v_accpet_view  WHERE del_flag = '0'" +
            "<if test=\"vac.carbrand != null and vac.carbrand != ''\">" +
            "AND carbrand like concat('%',#{vac.carbrand},'%')</if>" +
            "<if test=\"vac.carmaster != null and vac.carmaster != ''\">" +
            "AND carmaster like concat('%',#{vac.carmaster},'%')</if>" +
            "<if test=\"vac.name != null and vac.name != ''\">" +
            "AND `name` like concat('%',#{vac.name},'%')</if>" +
            "<if test=\"page != null and size != null\">" +
            "limit #{page},#{size}</if>" +
            "</script>"})
    @Results(id = "AcceptResult",
            value = {
                    @Result(property = "createTime", column = "create_time"),
                    @Result(property = "updateTime", column = "update_time"),
                    @Result(property = "createBy", column = "create_by"),
                    @Result(property = "updateBy", column = "update_by"),
            })
    List<VAccpetView> getAcceptByPage(Integer page, Integer size, VAccpetView vac, Date[] beginDateScope);

    @Select({"<script> " +
            "SELECT count(*) FROM v_accpet_view  WHERE del_flag = '0'" +
            "<if test=\"vac.carbrand != null and vac.carbrand != ''\">" +
            "AND carbrand like concat('%',#{vac.carbrand},'%')</if>" +
            "<if test=\"vac.carmaster != null and vac.carmaster != ''\">" +
            "AND carmaster like concat('%',#{vac.carmaster},'%')</if>" +
            "<if test=\"vac.name != null and vac.name != ''\">" +
            "AND `name` like concat('%',#{vac.name},'%')</if>" +
            "</script>"})
    Long getTotal(VAccpetView vac, Date[] beginDateScope);

    @Update("UPDATE acceptins SET accept=#{accept},update_time=#{updateTime},update_by=#{updateBy} WHERE id = #{id}")
    int handleAccept(Acceptins acceptins);
}
