package com.jk.service;

import com.jk.dto.Content;
import com.jk.dto.User;

import java.util.List;

public interface HomeService {

    List<Content> queryContent();

    User login(String username);

    String register(User user);

}
