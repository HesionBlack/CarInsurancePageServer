package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.InsuranceMapper;
import org.javaboy.vhr.model.Car;
import org.javaboy.vhr.model.Insurance;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ClassName: InsuranceService <br/>
 * Description: <br/>
 * date: 2020/3/1 10:15<br/>
 *
 * @author Hesion<br />
 * @since JDK 1.8
 */
@Service
public class InsuranceService {
    @Autowired
    InsuranceMapper insuranceMapper;
    public RespPageBean getInsuranceByPage(Integer page, Integer size, Insurance insurance, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Insurance> data = insuranceMapper.getInsuranceByPage(page, size,insurance,beginDateScope);
        Long total = insuranceMapper.getTotal(insurance, beginDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public int deleteInsByEid(String id) {
        return insuranceMapper.deleteInsByEid(id);
    }

    public int addIns(Insurance ins) {
        String id = UUID.randomUUID().toString();
        String substring = id.substring(0, 20);
        ins.setId(substring);
        ins.setCreateTime(new Date());
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        ins.setCreateBy(name);
        return insuranceMapper.addIns(ins);
    }

    public int updateIns(Insurance ins) {
        ins.setUpdateBy(SecurityContextHolder.getContext().getAuthentication().getName());
        ins.setUpdateTime(new Date());
        return insuranceMapper.updateIns(ins);
    }
}
