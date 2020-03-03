package org.javaboy.vhr.service;
/**
 * ClassName: ApplyInsService <br/>
 * Description: <br/>
 * date: 2020/3/2 20:20<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.javaboy.vhr.mapper.ApplyInsMapper;
import org.javaboy.vhr.model.Acceptins;
import org.javaboy.vhr.model.ApplyBean;
import org.javaboy.vhr.model.InsList;
import org.javaboy.vhr.model.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: vhr
 * @description:
 * @author: hesion
 * @create: 2020-03-02 20:20
 **/
@Service
public class ApplyInsService {
    @Autowired
    ApplyInsMapper applyInsMapper;
    public List<Insurance> getList() {
        List<Insurance> list = applyInsMapper.getList();
        return list;
    }

    public int apply(ApplyBean applyBean) {
        Acceptins acceptins = new Acceptins();
        acceptins.setId(UUID.randomUUID().toString());
        acceptins.setCarId(applyBean.getCarId());
        acceptins.setFinalprice(applyBean.getFinalPrice());
        acceptins.setInsId(applyBean.getInsId());
        acceptins.setAccept(0);
        acceptins.setDuration(applyBean.getDuration());
        acceptins.setCreateTime(new Date());
        acceptins.setCreateBy(SecurityContextHolder.getContext().getAuthentication().getName());
        acceptins.setDel_flag("0");
        return applyInsMapper.apply(acceptins);
    }
}
