package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.javaboy.vhr.model.VAccpetView;

import java.util.List;

/**
 * ClassName: LookInsMapper <br/>
 * Description: <br/>
 * date: 2020/3/2 18:59<br/>
 *
 * @author Hesion<br />
 * @since JDK 1.8
 */
@Mapper
public interface LookInsMapper {
    @Select({"<script> " +
            "SELECT * FROM v_accpet_view  WHERE del_flag = '0' AND `carId`=#{carId}" +
            "<if test=\"page != null and size != null\">" +
            "limit #{page},#{size}</if>" +
            "</script>"})
    @Results(id = "LookInsInfoResult",
            value = {
                    @Result(property = "createTime", column = "create_time"),
                    @Result(property = "updateTime", column = "update_time"),
                    @Result(property = "createBy", column = "create_by"),
                    @Result(property = "updateBy", column = "update_by"),
            })
    List<VAccpetView> getLookInsByPage(Integer page, Integer size, String carId);
    @Select({"<script> " +
            "SELECT count(*) FROM v_accpet_view  WHERE del_flag = '0' AND `carId`=#{carId}" +
            "</script>"})
    Long getTotal(String carId);
}
