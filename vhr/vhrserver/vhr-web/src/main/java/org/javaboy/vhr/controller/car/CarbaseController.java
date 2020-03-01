package org.javaboy.vhr.controller.car; /**
 * ClassName: Car <br/>
 * Description: <br/>
 * date: 2020/2/29 7:50<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.javaboy.vhr.model.Car;
import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @program: vhr
 * @description:
 * @author: hesion
 * @create: 2020-02-29 07:50
 **/
@RestController
@RequestMapping("/car/basic")
public class CarbaseController {
    @Autowired
    CarService carService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Car car, Date[] beginDateScope) {
        return carService.getCarByPage(page, size, car, beginDateScope);
    }
    @PostMapping("/")
    public RespBean addCar(@RequestBody Car car) {
        if (carService.addCar(car) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteCarByEid(@PathVariable String id) {
        if (carService.deleteCarByEid(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @PutMapping("/")
    public RespBean updateCar(@RequestBody Car car) {
        if (carService.updateCar(car) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
