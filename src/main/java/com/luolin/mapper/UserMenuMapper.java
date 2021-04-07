package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.UserMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMenuMapper {

    int create(UserMenu userMenu);

    int delete(Integer id);

    int update(UserMenu userMenu);

    int updateSelective(UserMenu userMenu);

    List<UserMenu> query(UserMenu userMenu);

    UserMenu detail(Integer id);

    int count(UserMenu userMenu);

}
