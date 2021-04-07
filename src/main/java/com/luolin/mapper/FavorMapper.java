package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Favor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavorMapper {

    int create(Favor favor);

    int delete(Integer id);

    int update(Favor favor);

    int updateSelective(Favor favor);

    List<Favor> query(Favor favor);

    Favor detail(Integer id);

    int count(Favor favor);

}
