package org.javaboy.vhr.service;
/**
 * ClassName: LookInsService <br/>
 * Description: <br/>
 * date: 2020/3/2 18:58<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.javaboy.vhr.mapper.AcceptMapper;
import org.javaboy.vhr.mapper.LookInsMapper;
import org.javaboy.vhr.model.Car;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.VAccpetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: hesion
 * @create: 2020-03-02 18:58
 **/
@Service
public class LookInsService {
    @Autowired
    LookInsMapper lookInsMapper;
    public RespPageBean lookIns(String carId, Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<VAccpetView> data = lookInsMapper.getLookInsByPage(page, size,carId);
        Long total = lookInsMapper.getTotal(carId);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
