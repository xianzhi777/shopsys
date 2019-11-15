package com.woniu.interceptor;

import com.woniu.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorRepeat  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String id=httpServletRequest.getSession().getId();
        User user=(User) httpServletRequest.getSession().getAttribute("user");
        if(httpServletRequest.getSession().getServletContext().getAttribute(user.getId()+"")==null){
            return false;
        }
        String sessionid=httpServletRequest.getSession().getServletContext().getAttribute(user.getId()+"").toString();
        //System.out.println("id:"+id);
        //System.out.println("sessionid"+sessionid);
        if(!id.equals(sessionid)){
            httpServletRequest.getRequestDispatcher("../view/login.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
