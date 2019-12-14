package controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式转换
 */
public class BaseController {
    /**
     * 创建时间转换方法
     * 在控制器初始化的时候会自动去调用
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        //注册自定义编辑器              时间权限    使用自定义日期编辑器                  日期格式                    可以为空
        dataBinder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
    }
}
