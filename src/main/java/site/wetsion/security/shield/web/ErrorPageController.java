package site.wetsion.security.shield.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 异常页面
 *
 * @author weixin
 * @version 1.0
 * @CLassName ErrorPageController
 * @date 2020/1/10 8:01 PM
 */
@Controller
public class ErrorPageController {

    @GetMapping("/403")
    public String e403() {
        return "errors/403";
    }

    @GetMapping("/404")
    public String e404() {
        return "errors/404";
    }
}
