package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Train;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrainMapper {

    int create(Train train);

    int delete(Integer id);

    int update(Train train);

    int updateSelective(Train train);

    List<Train> query(Train train);

    Train detail(Integer id);

    int count(Train train);

}
