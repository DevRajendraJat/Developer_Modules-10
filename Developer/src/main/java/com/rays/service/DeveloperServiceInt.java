package com.rays.service;

import java.util.List;

import com.rays.dto.DeveloperDTO;

public interface DeveloperServiceInt {
	public Long add(DeveloperDTO dto);

	public void update(DeveloperDTO dto);

	public void delete(long id);

	public DeveloperDTO findById(long id);

	public List search(DeveloperDTO dto, int pageNo, int pageSize);
}
