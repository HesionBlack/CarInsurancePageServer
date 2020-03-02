package org.javaboy.vhr.service;
/**
 * ClassName: AcceptService <br/>
 * Description: <br/>
 * date: 2020/3/2 13:35<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.javaboy.vhr.mapper.AcceptMapper;
import org.javaboy.vhr.model.Acceptins;
import org.javaboy.vhr.model.Car;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.VAccpetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: hesion
 * @create: 2020-03-02 13:35
 **/
@Service
public class AcceptService {
    @Autowired
    AcceptMapper acceptMapper;
    public RespPageBean getAcceptByPage(Integer page, Integer size, VAccpetView vAccpetView, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<VAccpetView> data = acceptMapper.getAcceptByPage(page, size,vAccpetView,beginDateScope);
        Long total = acceptMapper.getTotal(vAccpetView, beginDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public int handleAccept(String id, Integer type) {
        Acceptins acceptins = new Acceptins();
        acceptins.setUpdateBy(SecurityContextHolder.getContext().getAuthentication().getName());
        acceptins.setUpdateTime(new Date());
        acceptins.setId(id);
        acceptins.setAccept(type);
        return acceptMapper.handleAccept(acceptins);
    }
}
