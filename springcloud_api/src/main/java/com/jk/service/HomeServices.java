package com.jk.service;

import com.jk.dto.Content;
import com.jk.dto.Story;
import com.jk.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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


}
