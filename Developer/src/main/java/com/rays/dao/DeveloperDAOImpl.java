package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.DeveloperDTO;

@Repository
public class DeveloperDAOImpl implements DeveloperDAOInt {

	@PersistenceContext
	public EntityManager entity;

	@Override
	public long add(DeveloperDTO dto) {
		entity.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(DeveloperDTO dto) {
		entity.merge(dto);
	}

	@Override
	public void delete(DeveloperDTO dto) {
		entity.remove(dto);
	}

	@Override
	public DeveloperDTO findByPk(long pk) {
		DeveloperDTO dto = entity.find(DeveloperDTO.class, pk);
		return dto;
	}

	@Override
	public List search(DeveloperDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entity.getCriteriaBuilder();
		CriteriaQuery<DeveloperDTO> cq = builder.createQuery(DeveloperDTO.class);
		Root<DeveloperDTO> qRoot = cq.from(DeveloperDTO.class);

		List<Predicate> predicatelList = new ArrayList<Predicate>();

		if (dto != null) {
			if (dto.getDeveloperName() != null && dto.getDeveloperName().length() > 0) {
				predicatelList.add(builder.like(qRoot.get("DeveloperName"), dto.getDeveloperName() + "%"));
			}

			if (dto.getId() != null && dto.getId() > 0) {
				predicatelList.add(builder.equal(qRoot.get("id"), dto.getId()));
			}
		}
		cq.where(predicatelList.toArray(new Predicate[predicatelList.size()]));

		TypedQuery<DeveloperDTO> tq = entity.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}
		List<DeveloperDTO> list = tq.getResultList();

		return list;
	}
}