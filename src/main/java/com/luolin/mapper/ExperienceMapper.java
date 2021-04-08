package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Experience;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExperienceMapper {

    int create(Experience experience);

    int delete(Integer id);

    int update(Experience experience);

    int updateSelective(Experience experience);

    List<Experience> query(Experience experience);

    Experience detail(Integer id);

    int count(Experience experience);

}
