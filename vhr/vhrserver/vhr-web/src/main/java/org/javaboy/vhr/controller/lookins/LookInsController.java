package org.javaboy.vhr.controller.lookins;
/**
 * ClassName: LookInsController <br/>
 * Description: <br/>
 * date: 2020/3/2 18:55<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.javaboy.vhr.model.Car;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.LookInsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @program: vhr
 * @description:
 * @author: hesion
 * @create: 2020-03-02 18:55
 **/
@RestController
@RequestMapping("/lookIns/basic")
public class LookInsController {
@Autowired
LookInsService lookInsService;

    @GetMapping("/{carId}")
    public RespPageBean lookIns(@PathVariable String carId,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return lookInsService.lookIns(carId,page,size);
    }

}
