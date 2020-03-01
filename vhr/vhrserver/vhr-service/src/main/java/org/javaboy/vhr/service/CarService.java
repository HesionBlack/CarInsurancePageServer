package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.CarinfoMapper;
import org.javaboy.vhr.model.Car;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ClassName: CarService <br/>
 * Description: <br/>
 * date: 2020/2/29 7:59<br/>
 *
 * @author Hesion<br />
 * @since JDK 1.8
 */
@Service
public class CarService {
    @Autowired
    CarinfoMapper carinfoMapper;

    public RespPageBean getCarByPage(Integer page, Integer size, Car car, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Car> data = carinfoMapper.getCarByPage(page, size,car,beginDateScope);
        Long total = carinfoMapper.getTotal(car, beginDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }


    public int deleteCarByEid(String id) {
        return carinfoMapper.deleteCarByEid(id);
    }

    public int addCar(Car car) {
        String id = UUID.randomUUID().toString();
        String substring = id.substring(0, 20);
        car.setId(substring);
        car.setCreateTime(new Date());
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        car.setCreateBy(name);
        if(car.getOutdanger()==null){
            car.setOutdanger(0);
        }
        return carinfoMapper.addCar(car);
    }

    public int updateCar(Car car) {
        car.setUpdateBy(SecurityContextHolder.getContext().getAuthentication().getName());
        car.setUpdateTime(new Date());
        return carinfoMapper.updateByPrimaryKeySelective(car);
    }
}
