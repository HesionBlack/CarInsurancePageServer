package org.javaboy.vhr.controller.insurance;
/**
 * ClassName: ApplyInsController <br/>
 * Description: <br/>
 * date: 2020/3/2 20:16<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.javaboy.vhr.mapper.InsuranceMapper;
import org.javaboy.vhr.model.InsList;
import org.javaboy.vhr.service.ApplyInsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: vhr
 * @description:
 * @author: hesion
 * @create: 2020-03-02 20:16
 **/
@RestController
@RequestMapping("/apply")
public class ApplyInsController {
    @Autowired
    ApplyInsService applyInsService;

    @GetMapping("/insList")
    public List<InsList> getList(){

        return applyInsService.getList();
    }
}
