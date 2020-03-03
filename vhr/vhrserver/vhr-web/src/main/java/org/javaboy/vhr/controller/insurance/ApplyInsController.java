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
import org.javaboy.vhr.model.*;
import org.javaboy.vhr.service.ApplyInsService;
import org.javaboy.vhr.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<Insurance> getList(){

        return applyInsService.getList();
    }

    @PostMapping("/")
    public RespBean apply(@RequestBody ApplyBean applyBean) throws IOException {
        if (applyInsService.apply(applyBean) == 1) {
            return RespBean.ok("申请成功");
        }
        return RespBean.error("申请失败");
    }
}
