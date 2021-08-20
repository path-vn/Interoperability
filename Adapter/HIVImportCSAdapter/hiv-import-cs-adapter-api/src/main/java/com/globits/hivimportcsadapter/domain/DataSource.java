package com.globits.hivimportcsadapter.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.globits.core.domain.BaseObject;
import com.globits.core.domain.PersonAddress;

/**
 * Sample entity
 * @author dangnh
 * @since 2021/03/22
 */
@Entity
@Table(name = "tbl_data_source")
@XmlRootElement
public class DataSource extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")
	private String name;
	
	@Column(name="code",nullable = false,unique = true)
	private String code;
	
	@Column(name="description")
	private String description;

	@Column(name="channel_url",nullable = false,unique = true)
	private String channelUrl;
	
	@OneToMany(mappedBy = "dataSource", fetch = FetchType.LAZY, orphanRemoval = true)
	protected Set<DataFile> dataFiles;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}


	public Set<DataFile> getDataFiles() {
		return dataFiles;
	}

	public void setDataFiles(Set<DataFile> dataFiles) {
		this.dataFiles = dataFiles;
	}

}
