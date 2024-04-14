package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.AttachmentDAOInt;
import com.rays.dto.AttachmentDTO;

@Service

public class AttachmentServiceImpl implements AttachmentServiceInt {
	@Autowired
	public AttachmentDAOInt dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Long add(AttachmentDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AttachmentDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		AttachmentDTO dto = dao.findByPk(id);
		dao.delete(dto);
	}

	@Transactional(readOnly = true)
	public AttachmentDTO findById(long id) {

		AttachmentDTO dto = dao.findByPk(id);
		return dto;
	}

}
