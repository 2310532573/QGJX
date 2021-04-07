package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Type;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TypeMapper {

    int create(Type type);

    int delete(Integer id);

    int update(Type type);

    int updateSelective(Type type);

    List<Type> query(Type type);

    Type detail(Integer id);

    int count(Type type);

}
