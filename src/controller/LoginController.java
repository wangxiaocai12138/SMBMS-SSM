package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.User;
import service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    @Qualifier("userService")
    public UserService userService;

    /**
     * 首次进入系统，或者无权限后跳转到系统
     * @return
     */
    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }

    /**
     * 登录
     * @param userCode
     * @param userPassword
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/dologin.html",method = RequestMethod.POST)
    public String doLogin(@RequestParam String userCode,
                          @RequestParam String userPassword,
                          HttpServletRequest request, HttpSession session){
        User user=userService.login(userCode,userPassword);
        System.out.println("----"+userCode);
        if(user!=null){
            session.setAttribute("userSession",user);
            return "redirect:/sys/main.html";//通过自定义路径对非法请求拦截
        }else {
            request.setAttribute("error","用户名或者密码不正确!");
            return "login";
        }
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout.html")
    public String logout(HttpSession session){
        session.removeAttribute("userSession");
        return "login";
    }

    /**
     * 登录成功后进入主页
     * @return
     */
    @RequestMapping("/sys/main.html")
    public String main(){
        return "frame";
    }


}
