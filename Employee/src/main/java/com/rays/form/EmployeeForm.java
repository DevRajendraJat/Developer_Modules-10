package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.EmployeeDTO;

public class EmployeeForm extends BaseForm {

	@NotEmpty(message = "Name is Required")
	private String name;

	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}", message = "Invalid Id")
	@NotEmpty(message = "Login ID is required")
	private String loginId;

	@NotEmpty(message = "Name is Required")
	private String password;

	@NotNull(message = "Please enter date of birth")
	private String dob;

	@NotEmpty(message = "Address is Required")
	private String address;

	@NotEmpty(message = "DepartMent is Required")
	private String department;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@NotEmpty(message = "phoneNo number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
	private String phoneNo;

	@Override
	public BaseDTO getDTO() {
		EmployeeDTO dto = initDTO(new EmployeeDTO());
		dto.setName(name);
		dto.setLoginId(loginId);
		dto.setPassword(password);
		dto.setDepartment(department);
		if (dob != null && !dob.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(dob);
				dto.setDob(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}
		if (phoneNo != null && !phoneNo.isEmpty()) {
			dto.setPhoneNo(Long.valueOf(phoneNo));
		}
		return dto;
	}
}
