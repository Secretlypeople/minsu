package com.jk.mapper;

import com.jk.dto.Content;
import com.jk.dto.Story;
import com.jk.dto.User;

import java.util.List;

public interface HomeMapper {


    List<Content> queryContent();

    User login(String username);

    String register(User user);

    List<Story> queryStory();

    Story queryStoryById(Integer id);
}
