package com.luolin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luolin.entity.Certificate;
import com.luolin.mapper.CertificateMapper;
import com.luolin.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service

public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateMapper certificateMapper;

    @Override
    public int create(Certificate certificate) {
        return certificateMapper.create(certificate);
    }

    @Override
    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if (!StringUtils.isEmpty(s)) {
                certificateMapper.delete(Integer.parseInt(s));
                row++;
            }
        }
        return row;
    }

    @Override
    public int delete(Integer id) {
        return certificateMapper.delete(id);
    }

    @Override
    public int update(Certificate certificate) {
        return certificateMapper.update(certificate);
    }

    @Override
    public int updateSelective(Certificate certificate) {
        return certificateMapper.updateSelective(certificate);
    }

    @Override
    public PageInfo<Certificate> query(Certificate certificate) {
        if (certificate != null && certificate.getPage() != null) {
            PageHelper.startPage(certificate.getPage(), certificate.getLimit());
        }
        return new PageInfo<Certificate>(certificateMapper.query(certificate));
    }

    @Override
    public Certificate detail(Integer id) {
        return certificateMapper.detail(id);
    }

    @Override
    public int count(Certificate certificate) {
        return certificateMapper.count(certificate);
    }

}
