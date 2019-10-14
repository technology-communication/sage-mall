package com.dls.web;

import com.dls.common.bean.ResultBuilder;
import com.dls.common.bean.ReturnResult;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张金行
 * @Title: HelloWorldController
 * @ProjectName sage-mall
 * @Description:
 * @date 2019/10/14 20:44
 */
@RestController
// autorefresh of configuration updates
@RefreshScope
public class HelloWorldController {
    @GetMapping("/home")
    public ReturnResult get() {
        return ResultBuilder.success();
    }
}
