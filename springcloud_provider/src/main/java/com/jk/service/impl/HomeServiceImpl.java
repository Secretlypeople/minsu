package com.jk.service.impl;

import com.jk.dto.Content;
import com.jk.dto.Story;
import com.jk.dto.User;
import com.jk.dto.UserInfo;
import com.jk.mapper.HomeMapper;
import com.jk.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private HomeMapper homeMapper;

    @Override
    public List<Content> queryContent() {
        return homeMapper.queryContent();
    }

    @Override
    public User login(String username) {
        return homeMapper.login(username);
    }

    @Override
    public String register(User user) {
        return homeMapper.register(user);
    }

    @Override
    public List<Story> queryStory() {
        return homeMapper.queryStory();
    }

    @Override
    public Story queryStoryById(Integer id) {
        return homeMapper.queryStoryById(id);
    }

    @Override
    public List<Story> queryMyworks(Integer userId) {
        return homeMapper.queryMyworks(userId);
    }

    @Override
    public UserInfo queryInstall(Integer userId) {
        return homeMapper.queryInstall(userId);
    }

    @Override
    public void upateSigcard(UserInfo userInfo) {
        homeMapper.upateSigcard(userInfo);
    }

    @Override
    public void upateEmail(UserInfo userInfo) {
        homeMapper.upateEmail(userInfo);
    }

    @Override
    public void upatePhone(UserInfo userInfo) {
        homeMapper.upatePhone(userInfo);
    }


}
