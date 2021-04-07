package com.luolin.service;

import com.luolin.entity.Certificate;
import com.github.pagehelper.PageInfo;

public interface CertificateService {

    int create(Certificate certificate);

    int delete(String ids);

    int delete(Integer id);

    int update(Certificate certificate);

    int updateSelective(Certificate certificate);

    PageInfo<Certificate> query(Certificate certificate);

    Certificate detail(Integer id);

    int count(Certificate certificate);
}
