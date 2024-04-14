package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class DeveloperForm {
	
	private Long id;
	
	
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

	@NotEmpty(message = "DeveloperName is required")
	private String DeveloperName;

	@NotEmpty(message = "work is required")
	private String work;

	@NotEmpty(message = "project is required")
	private String project;

	@NotEmpty(message = "status is required")
	private String status;

	private Date submitDate;
}
