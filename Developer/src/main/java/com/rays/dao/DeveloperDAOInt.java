package com.rays.dao;

import java.util.List;

import com.rays.dto.DeveloperDTO;

public interface DeveloperDAOInt {

	public long add(DeveloperDTO dto);

	public void update(DeveloperDTO dto);

	public void delete(DeveloperDTO dto);

	public DeveloperDTO findByPk(long pk);

	public List search(DeveloperDTO dto, int pageNo, int pageSize);
}
