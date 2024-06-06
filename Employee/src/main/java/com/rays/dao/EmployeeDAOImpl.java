package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.EmployeeDTO;

@Repository
public class EmployeeDAOImpl extends BaseDAOImpl<EmployeeDTO> implements EmployeeDAOInt {

	@Override
	public Class<EmployeeDTO> getDTOClass() {

		return EmployeeDTO.class;
	}

	@Override
	public List<Predicate> getWhereClause(CriteriaBuilder builder, Root qRoot, EmployeeDTO dto) {
		List<Predicate> whereConditon = new ArrayList<Predicate>();

		if (dto.getName() != null && dto.getName().length() > 0) {
			whereConditon.add(builder.like(qRoot.get("name"), dto.getName()));
		}

		return whereConditon;
	}

}