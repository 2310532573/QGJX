package com.luolin.service;

import com.luolin.entity.Send;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface SendService {

    void create(Send send);

    void delete(String ids);

    int delete(Integer id);

    void update(Send send);

    int updateSelective(Send send);

    PageInfo<Send> query(Send send);

    Send detail(Integer id);

    int count(Send send);
}
