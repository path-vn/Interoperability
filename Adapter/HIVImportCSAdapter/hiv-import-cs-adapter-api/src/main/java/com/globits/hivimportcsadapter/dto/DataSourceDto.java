package com.globits.hivimportcsadapter.dto;

import java.util.HashSet;
import java.util.Set;

import com.globits.core.dto.BaseObjectDto;
import com.globits.hivimportcsadapter.domain.DataFile;
import com.globits.hivimportcsadapter.domain.DataSource;

public class DataSourceDto extends BaseObjectDto {
	private String name;
	private String code;
	private String description;
	private String channelUrl;
	protected Set<DataFileDto> dataFiles;
	public DataSourceDto() {
		super();
	}
	public DataSourceDto(DataSource entity,boolean simple) {
		if(entity != null) {
			this.id = entity.getId();
			this.name = entity.getName();
			this.code = entity.getCode();
			this.description = entity.getDescription();
			this.channelUrl= entity.getChannelUrl();
			if(simple) {
				if(entity.getDataFiles() != null && entity.getDataFiles().size() >0) {
					Set<DataFileDto> listFiles = new HashSet<DataFileDto>();
					for (DataFile dataFileDto : entity.getDataFiles()) {
						listFiles.add(new DataFileDto(dataFileDto,false));
					}
					this.dataFiles = listFiles;
				}
			}
		}
	}
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
	public Set<DataFileDto> getDataFiles() {
		return dataFiles;
	}
	public void setDataFiles(Set<DataFileDto> dataFiles) {
		this.dataFiles = dataFiles;
	}
	public String getChannelUrl() {
		return channelUrl;
	}
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	
	
}
