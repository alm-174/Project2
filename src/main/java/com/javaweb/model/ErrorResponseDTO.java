package com.javaweb.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponseDTO {
	private String errorString;
	private List<String> detaiList = new ArrayList<String>();
	public String getErrorString() {
		return errorString;
	}
	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}
	public List<String> getDetaiList() {
		return detaiList;
	}
	public void setDetaiList(List<String> detaiList) {
		this.detaiList = detaiList;
	}
	
}
