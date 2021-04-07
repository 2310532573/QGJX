package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int create(User user);

    int delete(Integer id);

    int update(User user);

    int updateSelective(User user);

    List<User> query(User user);

    User detail(Integer id);

    int count(User user);

}
