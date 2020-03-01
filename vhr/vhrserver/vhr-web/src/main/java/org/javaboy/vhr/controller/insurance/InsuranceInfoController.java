package org.javaboy.vhr.controller.insurance;
/**
 * ClassName: InsuranceInfoController <br/>
 * Description: <br/>
 * date: 2020/3/1 10:13<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.javaboy.vhr.model.Car;
import org.javaboy.vhr.model.Insurance;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @program: vhr
 * @description:
 * @author: hesion
 * @create: 2020-03-01 10:13
 **/
@RestController
@RequestMapping("/insurance/basic")
public class InsuranceInfoController {
    @Autowired
    InsuranceService insuranceService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Insurance insurance, Date[] beginDateScope) {
        return insuranceService.getInsuranceByPage(page, size, insurance, beginDateScope);
    }

    @DeleteMapping("/{id}")
    public RespBean deleteInsByEid(@PathVariable String id) {
        if (insuranceService.deleteInsByEid(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
    @PostMapping("/")
    public RespBean addIns(@RequestBody Insurance ins) {
        if (insuranceService.addIns(ins) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
    @PutMapping("/")
    public RespBean updateCar(@RequestBody Insurance ins) {
        if (insuranceService.updateIns(ins) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
