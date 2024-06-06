package com.rays.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseDTO {
	@Id
	@GeneratedValue(generator = "Naman")
	@GenericGenerator(name = "Naman", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false)

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
