package com.jk.service;

import com.jk.dto.Content;
import com.jk.dto.Story;
import com.jk.dto.User;
import com.jk.dto.UserInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface HomeServices {

    @GetMapping("queryContent")
    List<Content> queryContent();

    @PostMapping("login")
    User login(@RequestBody User user);

    @PostMapping("register")
    String register(@RequestBody User user);

    @GetMapping("queryStory")
    List<Story> queryStory();

    @GetMapping("queryStoryById")
    Story queryStoryById(@RequestParam("id") Integer id);

    @GetMapping("queryMywork")
     List<Story> queryMyworks(@RequestParam("userId")Integer userId);

    @GetMapping("queryInstall")
    UserInfo queryInstall(@RequestParam("userId")Integer userId);

    @PutMapping("upateInstall")
    void upateSigcard(@RequestBody UserInfo userInfo);

    @PutMapping("upateEmail")
    void upateEmail(@RequestBody UserInfo userInfo);

    @PutMapping("upatePhone")
    void upatePhone(@RequestBody UserInfo userInfo);



}
