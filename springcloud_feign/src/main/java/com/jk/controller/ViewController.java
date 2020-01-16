package com.jk.controller;

import com.jk.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    @RequestMapping("toViewName")
    public String toViewName(String viewName,HttpSession session) {

        User user = (User) session.getAttribute("user");

        if(user == null ){
            User user1 = new User();
            user1.setUserId(0);
            user1.setUsername("未登录");
            session.setAttribute("user",user1);
        }

        return viewName;
    }
}
