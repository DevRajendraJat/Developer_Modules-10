package com.rays.service;

import com.rays.dto.AttachmentDTO;

public interface AttachmentServiceInt {

	public Long add(AttachmentDTO dto);

	public void update(AttachmentDTO dto);

	public void delete(long id);

	public AttachmentDTO findById(long id);

}
