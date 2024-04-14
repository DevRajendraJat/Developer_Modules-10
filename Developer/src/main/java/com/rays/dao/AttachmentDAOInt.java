package com.rays.dao;

import com.rays.dto.AttachmentDTO;

public interface AttachmentDAOInt {

	public Long add(AttachmentDTO dto);

	public void update(AttachmentDTO dto);

	public void delete(AttachmentDTO dto);

	public AttachmentDTO findByPk(long pk);

}
