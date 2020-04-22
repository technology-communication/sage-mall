package com.dls.web;

import com.dls.web.bean.pojo.Product;
import com.dls.web.mapper.ProductMapper;
import com.dls.web.service.ProductService;
import com.dls.web.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author 张金行
 * @Title: HelloWorldController
 * @ProjectName sage-mall
 * @Description:
 * @date 2019/10/14 20:44
 */
@Controller
// autorefresh of configuration updates
// @RefreshScope
public class HelloWorldController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ProductService productService;

    @GetMapping("/login.html")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if ("admin".equals(username) && "123456".equals(password)) {
            return "search";
        }
        return "login";
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseEntity<Page<Product>> search(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        Page<Product> byProductName = productService.findByProductName(keyword);
        //redisUtil.set(keyword,product);
        return ResponseEntity.ok(byProductName);
    }

    @PostMapping("save")
    @ResponseBody
    public ResponseEntity save(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok(product);
    }
}
