package com.globits.hivimportcsadapter.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.hivimportcsadapter.domain.DataFile;
import com.globits.hivimportcsadapter.domain.DataSource;

public class DataFileDto extends BaseObjectDto{
	private String fileName;
	private String filePath;
	private String extension;
	private Long contentSize;
	private String contentType;
	private byte[] contentByte;
	protected DataSourceDto dataSource;
	public DataFileDto() {
	}
	public DataFileDto(DataFile entity,boolean simple) {
		if(entity != null) {
			this.id = entity.getId();
			this.fileName = entity.getFileName();
			this.filePath = entity.getFilePath();
			this.extension = entity.getExtension();
			this.contentType = entity.getContentType();
			
			if(entity.getContentByte() != null && entity.getContentByte().length >0) {
				byte[] listByte =new byte[entity.getContentByte().length];
				for (int i = 0; i < listByte.length; i++) {
					listByte[i]=entity.getContentByte()[i];
				}
				this.contentByte = listByte;
			}
			if(simple) {
				if(entity.getDataSource() != null) {
					this.dataSource = new DataSourceDto(entity.getDataSource(),false);
				}
			}
			
		}
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public Long getContentSize() {
		return contentSize;
	}
	public void setContentSize(Long contentSize) {
		this.contentSize = contentSize;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public byte[] getContentByte() {
		return contentByte;
	}
	public void setContentByte(byte[] contentByte) {
		this.contentByte = contentByte;
	}
	public DataSourceDto getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSourceDto dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
}
