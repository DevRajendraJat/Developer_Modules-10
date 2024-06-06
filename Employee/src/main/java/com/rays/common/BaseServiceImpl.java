package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

	@Autowired
	public D Basedao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long add(T dto) {
		Long pk = Basedao.add(dto);
		return pk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto) {
		Basedao.update(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public T findById(Long pk) {
		T dto = Basedao.findByPk(pk);
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List search(T dto, int pageNo, int pageSize) {
		List list = Basedao.search(dto, pageNo, pageSize);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		T dto = findById(id);
		Basedao.delete(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(T dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}

}
