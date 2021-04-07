package com.luolin.mapper;

import java.util.List;

import com.luolin.entity.Certificate;

public interface CertificateMapper {

	int create(Certificate certificate);

	int delete(Integer id);

	int update(Certificate certificate);

	int updateSelective(Certificate certificate);

	List<Certificate> query(Certificate certificate);

	Certificate detail(Integer id);

	int count(Certificate certificate);

}
