package com.globits.hivimportcsadapter.functiondto;

import java.util.UUID;

public class DataFileSearchDto extends SearchDto{
	private UUID dataSourceId;

	public UUID getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(UUID dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
	

}
