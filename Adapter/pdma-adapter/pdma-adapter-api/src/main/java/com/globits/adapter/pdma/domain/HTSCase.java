package com.globits.adapter.pdma.domain;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.pdma.types.Gender;
import com.globits.adapter.pdma.types.HTSYesNoNone;
import com.globits.adapter.pdma.types.HTSc12;
import com.globits.adapter.pdma.types.HTSc131;
import com.globits.adapter.pdma.types.HTSc132;
import com.globits.adapter.pdma.types.HTSc14;
import com.globits.adapter.pdma.types.HTSc17;
import com.globits.adapter.pdma.types.HTSc18;
import com.globits.adapter.pdma.types.HTSc20;
import com.globits.adapter.pdma.types.HTSc24;
import com.globits.adapter.pdma.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.pdma.utils.UUIDAttributeConverter;

@Entity
@Table(name = "tbl_hts_case")
public class HTSCase {
	@Transient
	private static final long serialVersionUID = -300384104809700661L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
//	@Convert(converter = UUIDAttributeConverter.class)
//	@Column(name = "uuid", unique = true, nullable = false, updatable = false, columnDefinition = "char(36)")
//	private UUID uid;
	
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
	
	@ManyToOne
	@JoinColumn(name = "c2_org_id", nullable = true)
	private Organization c2;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "c4_counselling_date", nullable = true)
	private LocalDateTime c4;

	
	//C6. Mã số khách hàng:
	@Column(name = "c6_client_code", nullable = true)
	private String c6;
	
	//C7. Giới tính khi sinh:
	@Column(name = "c7_gender", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private Gender c7;
	
	//C8. Năm sinh:
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "c8_dob", nullable = true)
	private LocalDateTime c8;
	
	//C9. Khách hàng thuộc nhóm nguy cơ nào?
	@OneToMany(mappedBy = "htsCase", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
//	@OrderBy("seqByOrganization")
	private Set<HTSCaseRiskGroup> c9 = new LinkedHashSet<>();
	
	//Phần ghi rõ của C9
	@Column(name = "c9_note", nullable = true)
	private String c9Note;
	

	
//	C12. Kết quả XN HIV lần gần nhất?
	@Column(name = "c12", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSc12 c12;
	
//	C13.1. Thời gian XN:
	@Column(name = "c13_1", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSc131 c131;
	
//	C13.2. Điều trị ARV:
	@Column(name = "c13_2", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSc132 c132;
	
//	C14. Kết quả XN HIV khẳng định lần này?
	@Column(name = "c14", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSc14 c14;
	
//	C15. KH được TV sau XN và nhận KQXN HIV?
	@Column(name = "c15", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSYesNoNone c15;
	
	@Column(name = "c15_note", nullable = true)
	private String c15Note;
	
	//C15. Ngày quay lại nhận kQXN:
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "c15_date", nullable = true)
	private LocalDateTime c15Date;
	
//	C16/C27. Giới thiệu chuyển gửi đến dịch vụ PrEP?
	@Column(name = "c16_27", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSYesNoNone c1627;
//	a. Tên cơ sở điều trị:
	@Column(name = "c16_27_note", nullable = true)
	private String c1627Note;
//	b. Ngày nhận dịch vụ:
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "c16_27_date", nullable = true)
	private LocalDateTime c1627Date;
	
	
//	C17. Kết quả XN mới nhiễm HIV?
	@Column(name = "c17", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSc17 c17;	
	
//	C18. Kết quả XN tải lượng vi-rút?
	@Column(name = "c18", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSc18 c18;
	
//	C19. Phòng XN khẳng định:
	@Column(name = "c19", nullable = true)
	private String c19;
//	- Ngày XN khẳng định:
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "c19_date", nullable = true)
	private LocalDateTime c19Date;
//	- Mã số XN khẳng định
	@Column(name = "c19_note", nullable = true)
	private String c19Note;
//	C20. Khách hàng nhận dịch vụ điều trị HIV?
	@Column(name = "c20", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSc20 c20;
//	Không - Ghi rõ lý do:
	@Column(name = "c20_reason", nullable = true)
	private String c20Reason;
//	Có
//	- Cơ sở tiếp nhận:
	@Column(name = "c20_org", nullable = true)
	private String c20Org;
//	- Ngày đăng ký điều trị:
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "c20_reg_date", nullable = true)
	private LocalDateTime c20RegDate;
//	- Mã số điều trị:
	@Column(name = "c20_code", nullable = true)
	private String c20Code;
	
//	C21. Được tư vấn về dịch vụ TBXNBT/BC?
	@Column(name = "c21", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSYesNoNone c21;
//	- Ghi rõ ngày tư vấn:
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "c21_date", nullable = true)
	private LocalDateTime c21Date;
	
//	C22. Đồng ý nhận dịch vụ TBXNBT/BC?
	@Column(name = "c22", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSYesNoNone c22;
	
//	C23. Thông tin nhân khẩu, xã hội
//	1. Họ và tên:
	@Column(name = "c23_full_name", nullable = true)
	private String c23FullName;
//	2. Dân tộc:
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "c23_ethnic_id", nullable = true)
	private Dictionary c23Ethnic;
//	3. Nghề nghiệp:
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "c23_profession_id", nullable = true)
	private Dictionary c23Profession;
//	4. Số CMND/CCCD:
	@Column(name = "c23_id_number", nullable = true)
	private String c23IdNumber;
	
//	 Số thẻ bhyt:
	@Column(name = "c23_health_number", nullable = true)
	private String c23HealthNumber;
	
//	5. Số điện thoại:
	@Column(name = "c23_phone_number", nullable = true)
	private String c23PhoneNumber;
//	6. Ghi chú:
	@Column(name = "c23_note", nullable = true)
	private String c23Note;
	
//	7. Hộ khẩu: - Tỉnh/TP
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "c23_resident_address_province_id", nullable = true)
	private AdminUnit c23ResidentAddressProvince;
	
//	- Quận/huyện:
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "c23_resident_address_district_id", nullable = true)
	private AdminUnit c23ResidentAddressDistrict;
	
//	- Phường/xã:
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "c23_resident_address_commune_id", nullable = true)
	private AdminUnit c23ResidentAddressCommune;
	
//	- phường xã String:
	@Column(name = "c23_resident_address_ward", nullable = true)
	private String c23ResidentAddressWard;
	
//	- Thôn/xóm/đường/phố/số nhà:
	@Column(name = "c23_resident_address_detail", nullable = true)
	private String c23ResidentAddressDetail;
	
//	8. Hiện tại: - Tỉnh/TP
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "c23_current_address_province_id", nullable = true)
	private AdminUnit c23CurrentAddressProvince;
	
//	- Quận/huyện:
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "c23_current_address_district_id", nullable = true)
	private AdminUnit c23CurrentAddressDistrict;
	
//	- Phường/xã:
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "c23_current_address_commune_id", nullable = true)
	private AdminUnit c23CurrentAddressCommune;
	
//	- Phường xã String:
	@Column(name = "c23_current_address_ward", nullable = true)
	private String c23CurrentAddressWard;
	
//	- Thôn/xóm/đường/phố/số nhà:
	@Column(name = "c23_current_address_detail", nullable = true)
	private String c23CurrentAddressDetail;
	
//	C24. Kết quả xác minh ca HIV dương tính?
	@Column(name = "c24", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private HTSc24 c24;
	
	

	
	public HTSCase() {
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

	public LocalDateTime getC4() {
		return c4;
	}

	public void setC4(LocalDateTime c4) {
		this.c4 = c4;
	}

	public String getC6() {
		return c6;
	}

	public void setC6(String c6) {
		this.c6 = c6;
	}

	public Gender getC7() {
		return c7;
	}

	public void setC7(Gender c7) {
		this.c7 = c7;
	}

	public LocalDateTime getC8() {
		return c8;
	}

	public void setC8(LocalDateTime c8) {
		this.c8 = c8;
	}

	public Set<HTSCaseRiskGroup> getC9() {
		if(c9==null) {
			c9 = new LinkedHashSet<HTSCaseRiskGroup>();
		}
		return c9;
	}

	public void setC9(Set<HTSCaseRiskGroup> c9) {
		this.c9 = c9;
	}

	public HTSc12 getC12() {
		return c12;
	}

	public void setC12(HTSc12 c12) {
		this.c12 = c12;
	}

	public HTSc131 getC131() {
		return c131;
	}

	public void setC131(HTSc131 c131) {
		this.c131 = c131;
	}

	public HTSc132 getC132() {
		return c132;
	}

	public void setC132(HTSc132 c132) {
		this.c132 = c132;
	}

	public HTSc14 getC14() {
		return c14;
	}

	public void setC14(HTSc14 c14) {
		this.c14 = c14;
	}

	public HTSYesNoNone getC15() {
		return c15;
	}

	public void setC15(HTSYesNoNone c15) {
		this.c15 = c15;
	}

	public String getC15Note() {
		return c15Note;
	}

	public void setC15Note(String c15Note) {
		this.c15Note = c15Note;
	}

	public HTSYesNoNone getC1627() {
		return c1627;
	}

	public void setC1627(HTSYesNoNone c1627) {
		this.c1627 = c1627;
	}

	public String getC1627Note() {
		return c1627Note;
	}

	public void setC1627Note(String c1627Note) {
		this.c1627Note = c1627Note;
	}

	public LocalDateTime getC1627Date() {
		return c1627Date;
	}

	public void setC1627Date(LocalDateTime c1627Date) {
		this.c1627Date = c1627Date;
	}

	public HTSc17 getC17() {
		return c17;
	}

	public void setC17(HTSc17 c17) {
		this.c17 = c17;
	}

	public HTSc18 getC18() {
		return c18;
	}

	public void setC18(HTSc18 c18) {
		this.c18 = c18;
	}

	public String getC19() {
		return c19;
	}

	public void setC19(String c19) {
		this.c19 = c19;
	}

	public LocalDateTime getC19Date() {
		return c19Date;
	}

	public void setC19Date(LocalDateTime c19Date) {
		this.c19Date = c19Date;
	}

	public String getC19Note() {
		return c19Note;
	}

	public void setC19Note(String c19Note) {
		this.c19Note = c19Note;
	}

	public HTSc20 getC20() {
		return c20;
	}

	public void setC20(HTSc20 c20) {
		this.c20 = c20;
	}

	public String getC20Reason() {
		return c20Reason;
	}

	public void setC20Reason(String c20Reason) {
		this.c20Reason = c20Reason;
	}

	public String getC20Org() {
		return c20Org;
	}

	public void setC20Org(String c20Org) {
		this.c20Org = c20Org;
	}

	public LocalDateTime getC20RegDate() {
		return c20RegDate;
	}

	public void setC20RegDate(LocalDateTime c20RegDate) {
		this.c20RegDate = c20RegDate;
	}

	public String getC20Code() {
		return c20Code;
	}

	public void setC20Code(String c20Code) {
		this.c20Code = c20Code;
	}

	public HTSYesNoNone getC21() {
		return c21;
	}

	public void setC21(HTSYesNoNone c21) {
		this.c21 = c21;
	}

	public LocalDateTime getC21Date() {
		return c21Date;
	}

	public void setC21Date(LocalDateTime c21Date) {
		this.c21Date = c21Date;
	}

	public HTSYesNoNone getC22() {
		return c22;
	}

	public void setC22(HTSYesNoNone c22) {
		this.c22 = c22;
	}

	public String getC23FullName() {
		return c23FullName;
	}

	public void setC23FullName(String c23FullName) {
		this.c23FullName = c23FullName;
	}

	public Dictionary getC23Ethnic() {
		return c23Ethnic;
	}

	public void setC23Ethnic(Dictionary c23Ethnic) {
		this.c23Ethnic = c23Ethnic;
	}

	public Dictionary getC23Profession() {
		return c23Profession;
	}

	public void setC23Profession(Dictionary c23Profession) {
		this.c23Profession = c23Profession;
	}

	public String getC23IdNumber() {
		return c23IdNumber;
	}

	public void setC23IdNumber(String c23IdNumber) {
		this.c23IdNumber = c23IdNumber;
	}

	public String getC23PhoneNumber() {
		return c23PhoneNumber;
	}

	public void setC23PhoneNumber(String c23PhoneNumber) {
		this.c23PhoneNumber = c23PhoneNumber;
	}

	public String getC23Note() {
		return c23Note;
	}

	public void setC23Note(String c23Note) {
		this.c23Note = c23Note;
	}

	public AdminUnit getC23ResidentAddressProvince() {
		return c23ResidentAddressProvince;
	}

	public void setC23ResidentAddressProvince(AdminUnit c23ResidentAddressProvince) {
		this.c23ResidentAddressProvince = c23ResidentAddressProvince;
	}

	public AdminUnit getC23ResidentAddressDistrict() {
		return c23ResidentAddressDistrict;
	}

	public void setC23ResidentAddressDistrict(AdminUnit c23ResidentAddressDistrict) {
		this.c23ResidentAddressDistrict = c23ResidentAddressDistrict;
	}

	public AdminUnit getC23ResidentAddressCommune() {
		return c23ResidentAddressCommune;
	}

	public void setC23ResidentAddressCommune(AdminUnit c23ResidentAddressCommune) {
		this.c23ResidentAddressCommune = c23ResidentAddressCommune;
	}

	public String getC23ResidentAddressDetail() {
		return c23ResidentAddressDetail;
	}

	public void setC23ResidentAddressDetail(String c23ResidentAddressDetail) {
		this.c23ResidentAddressDetail = c23ResidentAddressDetail;
	}

	public AdminUnit getC23CurrentAddressProvince() {
		return c23CurrentAddressProvince;
	}

	public void setC23CurrentAddressProvince(AdminUnit c23CurrentAddressProvince) {
		this.c23CurrentAddressProvince = c23CurrentAddressProvince;
	}

	public AdminUnit getC23CurrentAddressDistrict() {
		return c23CurrentAddressDistrict;
	}

	public void setC23CurrentAddressDistrict(AdminUnit c23CurrentAddressDistrict) {
		this.c23CurrentAddressDistrict = c23CurrentAddressDistrict;
	}

	public AdminUnit getC23CurrentAddressCommune() {
		return c23CurrentAddressCommune;
	}

	public void setC23CurrentAddressCommune(AdminUnit c23CurrentAddressCommune) {
		this.c23CurrentAddressCommune = c23CurrentAddressCommune;
	}

	public String getC23CurrentAddressDetail() {
		return c23CurrentAddressDetail;
	}

	public void setC23CurrentAddressDetail(String c23CurrentAddressDetail) {
		this.c23CurrentAddressDetail = c23CurrentAddressDetail;
	}

	public HTSc24 getC24() {
		return c24;
	}

	public void setC24(HTSc24 c24) {
		this.c24 = c24;
	}

	public String getC23ResidentAddressWard() {
		return c23ResidentAddressWard;
	}

	public void setC23ResidentAddressWard(String c23ResidentAddressWard) {
		this.c23ResidentAddressWard = c23ResidentAddressWard;
	}

	public String getC23CurrentAddressWard() {
		return c23CurrentAddressWard;
	}

	public void setC23CurrentAddressWard(String c23CurrentAddressWard) {
		this.c23CurrentAddressWard = c23CurrentAddressWard;
	}

	public String getC9Note() {
		return c9Note;
	}

	public void setC9Note(String c9Note) {
		this.c9Note = c9Note;
	}

	public LocalDateTime getC15Date() {
		return c15Date;
	}

	public void setC15Date(LocalDateTime c15Date) {
		this.c15Date = c15Date;
	}

	public String getC23HealthNumber() {
		return c23HealthNumber;
	}

	public void setC23HealthNumber(String c23HealthNumber) {
		this.c23HealthNumber = c23HealthNumber;
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
