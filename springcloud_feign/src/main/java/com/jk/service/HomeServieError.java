package com.jk.service;

import com.jk.dto.Content;
import com.jk.dto.Story;
import com.jk.dto.User;
import com.jk.dto.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("error")//防止和原有request冲突
@Component//注入spring容器 通过spring创建具体的实例对象
public class HomeServieError implements HomeServiceFeign {

    @Override
    public List<Content> queryContent() {
        return null;
    }

    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public String register(User user) {
        return null;
    }

    @Override
    public List<Story> queryStory() {
        return null;
    }

    @Override
    public Story queryStoryById(Integer id) {
        return null;
    }

    @Override
    public List<Story> queryMyworks(Integer userId) {
        return null;
    }

    @Override
    public UserInfo queryInstall(Integer userId) {
        return null;
    }

    @Override
    public void upateSigcard(UserInfo userInfo) {

    }

    @Override
    public void upateEmail(UserInfo userInfo) {

    }

    @Override
    public void upatePhone(UserInfo userInfo) {

    }


}
