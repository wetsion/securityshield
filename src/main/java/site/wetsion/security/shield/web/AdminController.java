package site.wetsion.security.shield.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员请求处理
 *
 * @author weixin
 * @version 1.0
 * @CLassName AdminController
 * @date 2020/1/9 4:34 PM
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String index() {
        return "admin/index";
    }
}
