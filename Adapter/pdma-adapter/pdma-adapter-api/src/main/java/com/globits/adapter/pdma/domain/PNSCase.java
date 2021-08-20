package com.globits.adapter.pdma.domain;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.pdma.types.Gender;
import com.globits.adapter.pdma.types.PNSc11;
import com.globits.adapter.pdma.utils.LocalDateTimeAttributeConverter;

@Entity
@Table(name = "tbl_pns_case")
public class PNSCase {
	@Transient
	private static final long serialVersionUID = -300384104809700661L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "c2_org_id", nullable = true)
	private Organization c2;
	
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Column(name = "created_by", length = 100, nullable = false)
	private String createdBy;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "modify_date", nullable = true)
	private LocalDateTime modifyDate;

	@Column(name = "modified_by", length = 100, nullable = true)
	private String modifiedBy;
	
	@OneToOne(cascade = CascadeType.DETACH, orphanRemoval = false)
	@JoinColumn(name = "hts_case_id", nullable = true)
	private HTSCase htsCase;
	
	@OneToOne(cascade = CascadeType.DETACH, orphanRemoval = false)
	@JoinColumn(name = "case_id", nullable = true)
	private Case hivCase;
	
//	C4. Mã số khách hàng:
	@Column(name = "c4_client_code", nullable = true)
	private String c4;
	
	//C7. Họ tên người có HIV:
	@Column(name = "c7", nullable = true)
	private String c7;
	
	//C8. Giới tính khi sinh:
	@Column(name = "c8_gender", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private Gender c8;
	
//	C9. Năm sinh:
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "c6_dob", nullable = true)
	private LocalDateTime c9;
	
//	C10. Ngày XN khẳng định HIV+:
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "c10_hiv_confirm_date", nullable = true)
	private LocalDateTime c10;
	
//	C11. Tình trạng ĐT ARV?
	@Column(name = "c11_arv_treatment_status", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private PNSc11 c11;
		
	//C12. Khách hàng thuộc nhóm nguy cơ nào?
	@OneToMany(mappedBy = "pnsCase", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
//	@OrderBy("seqByOrganization")
 	private Set<PNSCaseRiskGroup> c12 = new LinkedHashSet<>();
	
	//Phần ghi rõ của 12
	@Column(name = "c12_note", nullable = true)
	private String c12Note;
	
	public PNSCase() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Organization getC2() {
		return c2;
	}

	public void setC2(Organization c2) {
		this.c2 = c2;
	}

	public HTSCase getHtsCase() {
		return htsCase;
	}

	public void setHtsCase(HTSCase htsCase) {
		this.htsCase = htsCase;
	}

	public Case getHivCase() {
		return hivCase;
	}

	public void setHivCase(Case hivCase) {
		this.hivCase = hivCase;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}
	
	public String getC7() {
		return c7;
	}

	public void setC7(String c7) {
		this.c7 = c7;
	}

	public Gender getC8() {
		return c8;
	}

	public void setC8(Gender c8) {
		this.c8 = c8;
	}

	public LocalDateTime getC9() {
		return c9;
	}

	public void setC9(LocalDateTime c9) {
		this.c9 = c9;
	}

	public LocalDateTime getC10() {
		return c10;
	}

	public void setC10(LocalDateTime c10) {
		this.c10 = c10;
	}

	public Set<PNSCaseRiskGroup> getC12() {
		if(this.c12==null) {
			this.c12 = new LinkedHashSet<PNSCaseRiskGroup>();
		}
		return c12;
	}

	public void setC12(Set<PNSCaseRiskGroup> c12) {
		this.c12 = c12;
	}

	public String getC12Note() {
		return c12Note;
	}

	public void setC12Note(String c12Note) {
		this.c12Note = c12Note;
	}

	public PNSc11 getC11() {
		return c11;
	}

	public void setC11(PNSc11 c11) {
		this.c11 = c11;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	

}
