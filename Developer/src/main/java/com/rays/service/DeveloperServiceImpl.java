package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.DeveloperDAOInt;
import com.rays.dto.DeveloperDTO;

@Service
@Controller
@Transactional
public class DeveloperServiceImpl implements DeveloperServiceInt {
	@Autowired
	public DeveloperDAOInt dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Long add(DeveloperDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(DeveloperDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		DeveloperDTO dto = dao.findByPk(id);
		dao.delete(dto);
	}

	@Transactional(readOnly = true)
	public DeveloperDTO findById(long id) {

		DeveloperDTO dto = dao.findByPk(id);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(DeveloperDTO dto, int pageNo, int pageSize) {

		List list = dao.search(dto, pageNo, pageSize);
		return list;
	}

}
