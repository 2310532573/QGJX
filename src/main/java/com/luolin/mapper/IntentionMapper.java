package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Intention;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IntentionMapper {

    int create(Intention intention);

    int delete(Integer id);

    int update(Intention intention);

    int updateSelective(Intention intention);

    List<Intention> query(Intention intention);

    Intention detail(Integer id);

    int count(Intention intention);

}
