package dto;

import java.time.LocalDateTime;

public class ClinicalStageDto {
	private static final long serialVersionUID = 1L;

	private Long id;

	private LocalDateTime evalDate;

	private int stage;


	private String note;

	public ClinicalStageDto() {

	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public LocalDateTime getEvalDate() {
		return evalDate;
	}

	public void setEvalDate(LocalDateTime evalDate) {
		this.evalDate = evalDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
