package controller;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Role;
import pojo.User;
import service.RoleService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/user")
public class UserController{

    @Autowired
    @Qualifier("userService")
    public UserService userService;
    @Autowired
    public RoleService roleService;

    /**
     * 首页显示
     * @param model
     * @param queryname
     * @param queryUserRole
     * @return
     */
    @RequestMapping("/userlist.html")
    public String getUserList(Model model, @RequestParam(required = false) String queryname,
                                @RequestParam(required = false) Integer queryUserRole,
                                @RequestParam(defaultValue = "0") Integer pageIndex){
        Integer index=0;
        if(pageIndex==0){
            pageIndex+=1;//第一次进入页面
        }else{
            index=(pageIndex-1)*5; //下标
        }
        List<User> ulist =userService.getUserList(queryname,queryUserRole, null, null,index);
        Integer totalCount=userService.getUserCount();//总记录数
        Integer totalPageCount=totalCount%5==0?totalCount/5:totalCount/5+1;//总页数
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("currentPageNo",pageIndex);
        model.addAttribute("totalPageCount",totalPageCount);
        List<Role> rlist=roleService.getRoleList();
        model.addAttribute("userList",ulist);
        model.addAttribute("roleList",rlist);
        return "userlist";
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/userAdd.html")
    public String useradd(@ModelAttribute("user")User user){/*跳转过程中添加一个模型，后期可以直接调用填充好的模型对象*/
        return "useradd";
    }

    /**
     * 用户名验证
     * @param userCode
     * @return
     */
    @RequestMapping("/ucode")
    @ResponseBody//用于异步请求
    public String userCode(@RequestParam String userCode){
        Map<String,String> resultMap=new HashMap<>();
        if(StringUtils.isNullOrEmpty(userCode)){//判断传入参数是否为空
            resultMap.put("userCode","exist");
        }else {
            List<User> ulist=userService.getUserList(null,null,userCode, null, null);
            if (ulist.size()>0){
                resultMap.put("userCode","exist");
            }else {
                resultMap.put("userCode","noexist");
            }
        }
        return JSONArray.toJSONString(resultMap);
    }

    /**
     * 添加页面的用户角色列表
     * @return
     */
    @RequestMapping("/rolelist")
    @ResponseBody
    public String getRoleList(){
        List<Role> rlist=roleService.getRoleList();
        return JSONArray.toJSONString(rlist);
    }

    /**
     * 添加
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/adduser.html")
    public String addUser(User user, HttpSession session){//拿出跳转到添加页面前的模型对象
        user.setCreatedBy(((User)(session.getAttribute("userSession"))).getId());
        user.setCreationDate(new Date());
        if(userService.addUser(user)){
            return "redirect:/sys/user/userlist.html";
        }
        return "useradd";
    }

    /**
     * 删除
     * @param uid
     * @return
     */
    @RequestMapping("/deluser")
    @ResponseBody
    public String delUser(@RequestParam Integer uid){
        Map<String,String> map=new HashMap<>();
        if(userService.delUser(uid)){
            map.put("delResult","true");
        }else {
            map.put("delResult","false");
        }
        return JSONArray.toJSONString(map);
    }

    /**
     * 查看用户详细信息
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping("/viewuser.html")
    public String viedUser(Model model,@RequestParam Integer uid){
        List<User> ulist=userService.getUserList(null,null,null,uid, null);
        User user=ulist.get(0);
        model.addAttribute("user",user);
        return "userview";
    }

    /**
     * 用户修改前查看
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping("/upduser.html")
    public String updUser(Model model,@RequestParam Integer uid,
                          @ModelAttribute("user")User user){
        List<User> ulist=userService.getUserList(null,null,null,uid, null);
        User u=ulist.get(0);
        model.addAttribute("user",u);
        return "usermodify";
    }

    @RequestMapping("updateUser.html")
    public String updateUser(User user,HttpSession session){
        user.setModifyBy(((User)(session.getAttribute("userSession"))).getId());
        user.setModifyDate(new Date());
        if(userService.updateUser(user)){
            return "redirect:/sys/user/userlist.html";
        }else{
            return "redirect:/sys/user/upduser.html?uid="+user.getId();
        }
    }
}
