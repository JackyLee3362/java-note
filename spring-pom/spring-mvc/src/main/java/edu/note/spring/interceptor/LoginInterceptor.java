package edu.note.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 登录检查逻辑
        if(true){
            return true;
        }
        String url = request.getRequestURI();
        log.info("经过拦截器，请求路径是{}", url);

        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        // 如果不为空则放行
        if (loginUser != null)
            return true;
        // 否则拦截
        // session.setAttribute("msg","请先登录");
        // response.sendRedirect("/");
        request.setAttribute("msg", "请先登录");
        request.getRequestDispatcher("/login").forward(request, response);

        return false;
        // return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 目标方法执行完成以后
     * 
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        log.info("目标方法执行完成");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 页面渲染以后
     * 
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("页面渲染完成");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
