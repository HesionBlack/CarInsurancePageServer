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
import org.javaboy.vhr.model.InsList;
import org.javaboy.vhr.model.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<InsList> getList() {
        List<InsList> insLists = new ArrayList<>();
        List<Insurance> list = applyInsMapper.getList();
        for (Insurance insurance : list) {
            InsList insList = new InsList();
            insList.setValue(insurance.getName());
            insList.setLable(insurance.getId());
          insLists.add(insList);
        }
        return insLists;
    }
}
