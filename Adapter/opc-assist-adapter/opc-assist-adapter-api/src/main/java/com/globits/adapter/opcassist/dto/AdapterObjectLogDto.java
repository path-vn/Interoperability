package com.globits.adapter.opcassist.dto;

public class AdapterObjectLogDto {
	private String status;
	private int totalPages;
	private long totalElements;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	
}
