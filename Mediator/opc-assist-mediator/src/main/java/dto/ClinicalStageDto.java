package dto;

import java.time.LocalDateTime;

public class ClinicalStageDto {
	private static final long serialVersionUID = 1L;

	private Long id;


	private int stage;

	private LocalDateTime evalDate;

	private String note;

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
