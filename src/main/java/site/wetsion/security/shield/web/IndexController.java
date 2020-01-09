package site.wetsion.security.shield.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.wetsion.security.shield.domain.SysUser;
import site.wetsion.security.shield.service.SysUserService;

/**
 * @author weixin
 * @version 1.0
 * @CLassName IndexController
 * @date 2020/1/9 4:35 PM
 */
@Controller
public class IndexController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", defaultValue = "false") boolean error,
            Model model) {
        if (error) {
            model.addAttribute("msg", "登录失败");
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginP(String username, String password) {
        SysUser user =
                sysUserService.getOne(
                        new QueryWrapper<SysUser>().eq("username", username));
        if (user.getPassword().equals(password)) {
            return "redirect:/";
        } else {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
