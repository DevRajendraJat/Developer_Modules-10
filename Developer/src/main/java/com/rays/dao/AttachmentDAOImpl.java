package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rays.dto.AttachmentDTO;

@Repository
public class AttachmentDAOImpl implements AttachmentDAOInt {

	@PersistenceContext
	public EntityManager ent;

	@Override
	public Long add(AttachmentDTO dto) {
		ent.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(AttachmentDTO dto) {
		ent.merge(dto);
	}

	@Override
	public void delete(AttachmentDTO dto) {
		ent.remove(dto);
	}

	@Override
	public AttachmentDTO findByPk(long pk) {
		AttachmentDTO dto = ent.find(AttachmentDTO.class, pk);
		return dto;
	}
}
