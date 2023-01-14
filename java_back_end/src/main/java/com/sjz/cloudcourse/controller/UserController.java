package com.sjz.cloudcourse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.sjz.cloudcourse.common.R;
import com.sjz.cloudcourse.entity.User;
import com.sjz.cloudcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    UserService userService;

    /**
     * 登录 根据用户名和密码查询用户信息
     *
     * @param ucenterMember 用户信息
     * @return 登录结果
     */
    @RequestMapping("/login")
    public R login(@RequestBody User ucenterMember, HttpServletResponse resp, HttpServletRequest request) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String username = ucenterMember.getUsername();
        String password = ucenterMember.getPassword();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", password);
        User one = userService.getOne(queryWrapper);
        System.out.println("one = " + one);
        Map<String, Object> userMap = userService.getMap(queryWrapper);
        if (one != null) {
            String pTime = password + System.currentTimeMillis();
            String keys = Base64.getEncoder().encodeToString(pTime.getBytes(StandardCharsets.UTF_8));
            Cookie cookie = new Cookie("us_key", username + "@" + keys);
            cookie.setMaxAge(60 * 24);
            cookie.setPath("/");
            resp.addCookie(cookie);
//            redisTemplate.opsForValue().set(username, keys);
//            存储用户的信息到redis中
            redisTemplate.opsForValue().set("user", one);
            return R.ok().put("userInfo", userMap);

        }
        return R.error("用户名或密码错误");

    }


    /**
     * 登出
     *
     * @return 注册结果
     */
    @RequestMapping("/logout")
    public R Logout(HttpServletRequest request, HttpServletResponse response) {
        //清空Http Session中的所有数据，销毁HTTP SESSION对象
        //结束会话
        request.getSession().invalidate();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("us_key")) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        return R.ok();
    }

}
