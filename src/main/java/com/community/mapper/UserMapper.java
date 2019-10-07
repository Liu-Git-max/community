package com.community.mapper;


import com.community.model.User;
import com.community.model.UserExample;

import java.util.List;

public interface UserMapper {

    List<User> selectByExample(UserExample userExample);

    void insert(User user);

    void updateByExampleSelective(User updateUser, UserExample example);

    User selectByPrimaryKey(Long creator);
}