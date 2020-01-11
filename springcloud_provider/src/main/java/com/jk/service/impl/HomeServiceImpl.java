package com.jk.service.impl;

import com.jk.dto.Content;
import com.jk.dto.User;
import com.jk.mapper.HomeMapper;
import com.jk.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
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



}
