package interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*拦截器*/
public class SysInterceptor extends HandlerInterceptorAdapter {
    /*重写方法*/
    @Override
    public boolean preHandle(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler) throws Exception {
        /*获取用户session*/
        User user=(User) request.getSession().getAttribute("userSession");
        if(user==null){//判断是否为空
            response.sendRedirect(request.getContextPath()+"/401.jsp");
            return false;
        }
        return true;
    }
}
