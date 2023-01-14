package com.sjz.cloudcourse.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sjz.cloudcourse.common.R;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.regex.Pattern;


//@Configuration
public class LoginConfig implements HandlerInterceptor {


    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }


//        System.out.println("请求头中的参数");
//        System.out.println(request.getHeader("token"));
//        System.out.println(request.getHeaders("token").toString());

//        return request.getHeader("token")!= null;
        return true;
    }


}
