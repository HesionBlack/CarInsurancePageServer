package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.javaboy.vhr.model.Acceptins;
import org.javaboy.vhr.model.Insurance;

import java.util.List;

/**
 * ClassName: ApplyInsMapper <br/>
 * Description: <br/>
 * date: 2020/3/2 20:21<br/>
 *
 * @author Hesion<br />
 * @since JDK 1.8
 */
@Mapper
public interface ApplyInsMapper {
    @Select("SELECT * FROM insurance WHERE del_flag='0'")
    List<Insurance> getList();

    @Insert("INSERT INTO acceptins VALUES(#{id},#{carId},#{insId},#{finalprice},#{accept},#{duration},#{createTime},#{createBy},#{updateTime},#{updateBy},#{del_flag})")
    int apply(Acceptins acceptins);
}
