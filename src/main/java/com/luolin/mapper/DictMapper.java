package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictMapper {

    int create(Dict dict);

    int delete(Integer id);

    int update(Dict dict);

    int updateSelective(Dict dict);

    List<Dict> query(Dict dict);

    Dict detail(Integer id);

    int count(Dict dict);

}
