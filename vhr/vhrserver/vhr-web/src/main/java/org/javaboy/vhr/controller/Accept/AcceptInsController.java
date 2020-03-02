package org.javaboy.vhr.controller.Accept;
/**
 * ClassName: AcceptInsController <br/>
 * Description: <br/>
 * date: 2020/3/2 13:33<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.javaboy.vhr.model.Car;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.model.VAccpetView;
import org.javaboy.vhr.service.AcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @program: vhr
 * @description:
 * @author: hesion
 * @create: 2020-03-02 13:33
 **/
@RestController
@RequestMapping("/accept/basic")
public class AcceptInsController {
    @Autowired
    AcceptService acceptService;

    @GetMapping("/")
    public RespPageBean getAcceptByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, VAccpetView vAccpetView, Date[] beginDateScope) {
        return acceptService.getAcceptByPage(page, size, vAccpetView, beginDateScope);
    }
    @PutMapping("/{id}/{type}")
    public RespBean handleAccept(@PathVariable String id,@PathVariable Integer type) {
        if (acceptService.handleAccept(id,type) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }


}
