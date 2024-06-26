package com.sprintmanagement.dto;

import lombok.Data;

@Data
public class Status {
	String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
