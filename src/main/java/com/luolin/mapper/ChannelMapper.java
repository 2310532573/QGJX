package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Channel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelMapper {

	int create(Channel channel);

	int delete(Integer id);

	int update(Channel channel);

	int updateSelective(Channel channel);

	List<Channel> query(Channel channel);

	Channel detail(Integer id);

	int count(Channel channel);

}
