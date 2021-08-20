package com.globits.hivimportcsadapter.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.globits.core.domain.BaseObject;
import com.globits.core.domain.Religion;

/**
 * Sample entity
 * @author dangnh
 * @since 2021/03/22
 */
@Entity
@Table(name = "tbl_data_file")
@XmlRootElement
public class DataFile extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="file_path",nullable = false)
	private String filePath;
	
	@Column(name = "extension")
	private String extension;
	
	@Column(name="content_size")
	private Long contentSize;
	
	@Column(name="content_type")
	private String contentType;

	@Column(name="content_byte")
	private byte[] contentByte;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "data_source_id", nullable = true)
	protected DataSource dataSource;
	
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

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
