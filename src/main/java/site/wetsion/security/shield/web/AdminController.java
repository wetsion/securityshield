package site.wetsion.security.shield.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author weixin
 * @version 1.0
 * @CLassName AdminController
 * @date 2020/1/9 4:34 PM
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    public String index() {
        return "admin/index";
    }
}
