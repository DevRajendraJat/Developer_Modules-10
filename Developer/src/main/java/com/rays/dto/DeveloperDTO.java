package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.rays.common.DropDownList;

@Entity
@Table(name = "ST_DEVELOPER")
public class DeveloperDTO implements DropDownList {
	@Id
	@GeneratedValue(generator = "rays")
	@GenericGenerator(name = "rays", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false)

	private Long id;

	@Column(name = "DEVELOPER_NAME", length = 50)
	private String DeveloperName;

	@Column(name = "WORK", length = 50)
	private String work;

	@Column(name = "PROJECT", length = 50)
	private String project;

	@Column(name = "STATUS", length = 50)
	private String status;

	@Column(name = "SUBMIT_DATE")
	private Date submitDate;

	@Column(name = "IMAGEID", length = 50)
	public Long imageId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeveloperName() {
		return DeveloperName;
	}

	public void setDeveloperName(String developerName) {
		DeveloperName = developerName;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	@Override
	public String getKey() {

		return id + " ";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return submitDate + " ";
	}

}
