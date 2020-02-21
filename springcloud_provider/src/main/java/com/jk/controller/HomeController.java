package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.jk.dto.*;
import com.jk.service.HomeService;
import com.jk.service.HomeServices;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController implements  HomeServices {

    @Autowired
    private HomeService homeService;

    @GetMapping("queryContent")
    public List<Content> queryContent() {

        List<Content> list = homeService.queryContent();
        return list;
    }

    @PostMapping("login")
    public User login(User user) {
        return homeService.login(user.getUsername());
    }

    @PostMapping("register")
    public String register(User user) {
        homeService.register(user);
        return "success";
    }

    @Override
    @GetMapping("queryStory")
    public List<Story> queryStory() {

            List<Story> list = homeService.queryStory();
            return list;
    }

    @Override
    @GetMapping("queryStoryById")
    public Story queryStoryById(Integer id) {

        return homeService.queryStoryById(id);
    }

    @Override
    @GetMapping("queryMywork")
    public List<Story> queryMyworks(Integer userId) {
        return homeService.queryMyworks(userId);
    }

    @Override
    @GetMapping("queryInstall")
    public UserInfo queryInstall(Integer userId) {
        return homeService.queryInstall(userId);
    }

    @Override
    public void upateSigcard(UserInfo userInfo) {
        homeService.upateSigcard(userInfo);
    }

    @Override
    public void upateEmail(UserInfo userInfo) {
        homeService.upateEmail(userInfo);
    }

    @Override
    public void upatePhone(UserInfo userInfo) {
        homeService.upatePhone(userInfo);
    }


    @RabbitListener(queues = "1906-wyh")
    public void receiveMethod(String message){
        System.out.println(message);

        System.out.println("---------------------------华丽的分割线------------------------");

        // 将json串转为对象
        Order order = JSON.parseObject(message, Order.class);

        System.out.println(order);

    }




}
