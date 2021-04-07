package com.luolin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luolin.common.enums.ErrorCodeAndMsg;
import com.luolin.common.exception.DatabaseException;
import com.luolin.entity.Send;
import com.luolin.mapper.SendMapper;
import com.luolin.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SendServiceImpl implements SendService {

    @Autowired
    private SendMapper sendMapper;

    @Override
    public void create(Send send) {
        sendMapper.create(send);
    }

    @Override
    public void delete(String ids) {
        String[] arr = ids.split(",");
        for (String s : arr) {
            if(!StringUtils.isEmpty(s)){
                try {
                    sendMapper.delete(Integer.parseInt(s));
                }catch (Exception e){
                    throw new DatabaseException("E00001", ErrorCodeAndMsg.DATA_DELETE_ERROR.getCode());
                }
            }
        }
    }

    @Override
    public int delete(Integer id) {
        return sendMapper.delete(id);
    }

    @Override
    public void update(Send send) {
        try {
            sendMapper.update(send);
        }catch (Exception e){
            throw new DatabaseException("E00001", ErrorCodeAndMsg.DATA_UPDATE_ERROR.getCode());
        }
    }

    @Override
    public int updateSelective(Send send) {
        return sendMapper.updateSelective(send);
    }

    @Override
    public PageInfo<Send> query(Send send) {
        if(send != null && send.getPage() != null){
            PageHelper.startPage(send.getPage(),send.getLimit());
        }
        return new PageInfo<Send>(sendMapper.query(send));
    }

    @Override
    public Send detail(Integer id) {
        return sendMapper.detail(id);
    }

    @Override
    public int count(Send send) {
        return sendMapper.count(send);
    }
}
