package com.globits.facility.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.globits.core.dto.BaseObjectDto;
import com.globits.facility.domain.FacilityAdministrativeUnit;

public class FacilityAdministrativeUnitDto extends BaseObjectDto {
	private String name;
	private String code;
	private Integer level;
	private String mapCode;
	private FacilityAdministrativeUnitDto parent;// Đơn vị cha
	private UUID parentId;
	private Set<FacilityAdministrativeUnitDto> subAdministrativeUnits;// Đơn vị con
	private List<FacilityAdministrativeUnitDto> children;
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getMapCode() {
		return mapCode;
	}
	public void setMapCode(String mapCode) {
		this.mapCode = mapCode;
	}
	public FacilityAdministrativeUnitDto getParent() {
		return parent;
	}
	public void setParent(FacilityAdministrativeUnitDto parent) {
		this.parent = parent;
	}
	public UUID getParentId() {
		return parentId;
	}
	public void setParentId(UUID parentId) {
		this.parentId = parentId;
	}
	public Set<FacilityAdministrativeUnitDto> getSubAdministrativeUnits() {
		return subAdministrativeUnits;
	}
	public void setSubAdministrativeUnits(Set<FacilityAdministrativeUnitDto> subAdministrativeUnits) {
		this.subAdministrativeUnits = subAdministrativeUnits;
	}
	public List<FacilityAdministrativeUnitDto> getChildren() {
		return children;
	}
	public void setChildren(List<FacilityAdministrativeUnitDto> children) {
		this.children = children;
	}

	public FacilityAdministrativeUnitDto() {
		super();
	}
	
	public FacilityAdministrativeUnitDto(FacilityAdministrativeUnit administrativeUnit,boolean simple, int child) {
		super();
		if (administrativeUnit != null) {
			this.id = administrativeUnit.getId();
			this.name = administrativeUnit.getName();
			this.code = administrativeUnit.getCode();
			this.level = administrativeUnit.getLevel();
			this.mapCode=administrativeUnit.getMapCode();
			if (administrativeUnit.getParent() != null) {
				this.parent = new FacilityAdministrativeUnitDto(administrativeUnit.getParent(),true, 1);
			}
		}
	}

	public FacilityAdministrativeUnitDto(FacilityAdministrativeUnit administrativeUnit) {
		super();
		if (administrativeUnit != null) {
			this.id = administrativeUnit.getId();
			this.name = administrativeUnit.getName();
			this.code = administrativeUnit.getCode();
			this.level = administrativeUnit.getLevel();
			this.mapCode=administrativeUnit.getMapCode();
			
			if(administrativeUnit.getParent()!=null) {
				FacilityAdministrativeUnit parent = administrativeUnit.getParent();
				parent.setId(administrativeUnit.getParent().getId());
				parent.setCode(administrativeUnit.getParent().getCode());
				parent.setName(administrativeUnit.getParent().getName());
				parent.setLevel(administrativeUnit.getParent().getLevel());
				parent.setMapCode(administrativeUnit.getParent().getMapCode());
				
				this.parent = new FacilityAdministrativeUnitDto(parent);
				this.parentId =administrativeUnit.getParent().getId();
			}
			
			Set<FacilityAdministrativeUnitDto> administrativeUnitsDtos = new HashSet<FacilityAdministrativeUnitDto>();
			if (administrativeUnit != null && administrativeUnit.getSubAdministrativeUnits() != null
					&& administrativeUnit.getSubAdministrativeUnits().size() > 0) {
				for (FacilityAdministrativeUnit adu : administrativeUnit.getSubAdministrativeUnits()) {
					FacilityAdministrativeUnitDto subAdministrativeUnitsDto = new FacilityAdministrativeUnitDto();
					subAdministrativeUnitsDto.setId(adu.getId());
					subAdministrativeUnitsDto.setCode(adu.getCode());
					subAdministrativeUnitsDto.setName(adu.getName());
					subAdministrativeUnitsDto.setMapCode(adu.getMapCode());
					
					administrativeUnitsDtos.add(subAdministrativeUnitsDto);
				}
				this.subAdministrativeUnits = administrativeUnitsDtos;
			}
		}
	}

	public FacilityAdministrativeUnitDto(FacilityAdministrativeUnit administrativeUnit, Boolean isParent) {
		super();
		if (administrativeUnit != null) {
			this.id = administrativeUnit.getId();
			this.name = administrativeUnit.getName();
			this.code = administrativeUnit.getCode();
			this.level = administrativeUnit.getLevel();
					
			if(administrativeUnit.getParent()!=null) {
				FacilityAdministrativeUnit parent = administrativeUnit.getParent();
				parent.setId(administrativeUnit.getParent().getId());
				parent.setCode(administrativeUnit.getParent().getCode());
				parent.setName(administrativeUnit.getParent().getName());
				parent.setLevel(administrativeUnit.getParent().getLevel());
				parent.setMapCode(administrativeUnit.getParent().getMapCode());
				
				this.parent = new FacilityAdministrativeUnitDto(parent,true);
			}
		}
	}
	
	public FacilityAdministrativeUnitDto(FacilityAdministrativeUnit administrativeUnit, int chi) {
		super();
		if (administrativeUnit != null) {
			this.id = administrativeUnit.getId();
			this.name = administrativeUnit.getName();
			this.code = administrativeUnit.getCode();
			this.level = administrativeUnit.getLevel();
			this.mapCode = administrativeUnit.getMapCode();
					
			if(administrativeUnit.getParent()!=null) {
				FacilityAdministrativeUnit parent = administrativeUnit.getParent();
				parent.setId(administrativeUnit.getParent().getId());
				parent.setCode(administrativeUnit.getParent().getCode());
				parent.setName(administrativeUnit.getParent().getName());
				parent.setLevel(administrativeUnit.getParent().getLevel());
				
				parent.setMapCode(administrativeUnit.getParent().getMapCode());
				
				this.parent = new FacilityAdministrativeUnitDto(parent);
			}
			
			Set<FacilityAdministrativeUnitDto> administrativeUnitsDtos = new HashSet<FacilityAdministrativeUnitDto>();
			if (administrativeUnit != null && administrativeUnit.getSubAdministrativeUnits() != null
					&& administrativeUnit.getSubAdministrativeUnits().size() > 0) {
				for (FacilityAdministrativeUnit adu : administrativeUnit.getSubAdministrativeUnits()) {
					FacilityAdministrativeUnitDto subAdministrativeUnitsDto = new FacilityAdministrativeUnitDto();
					subAdministrativeUnitsDto.setId(adu.getId());
					subAdministrativeUnitsDto.setCode(adu.getCode());
					subAdministrativeUnitsDto.setName(adu.getName());
					administrativeUnitsDtos.add(subAdministrativeUnitsDto);

				}
				this.subAdministrativeUnits = administrativeUnitsDtos;
			}
			this.setChildren(getListChildren(administrativeUnit));
		}
	}

	
	private List<FacilityAdministrativeUnitDto> getListChildren(FacilityAdministrativeUnit unit){
		List<FacilityAdministrativeUnitDto> ret = new ArrayList<FacilityAdministrativeUnitDto>();
		
		if(unit != null && unit.getSubAdministrativeUnits()!=null && unit.getSubAdministrativeUnits().size()>0) {
			for(FacilityAdministrativeUnit s : unit.getSubAdministrativeUnits()) {
				FacilityAdministrativeUnitDto sDto = new FacilityAdministrativeUnitDto();
				sDto.setId(s.getId());
				sDto.setCode(s.getCode());
				sDto.setName(s.getName());
				sDto.setLevel(s.getLevel());
				sDto.setChildren(getListChildren(s));
				sDto.setMapCode(s.getMapCode());
				
				ret.add(sDto);
			}
		}
		return ret;
	}

}
