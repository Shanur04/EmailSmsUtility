package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the icg_setting database table.
 * 
 */
@Entity
@Table(name="icg_setting")
@NamedQuery(name="IcgSetting.findAll", query="SELECT i FROM IcgSetting i")
public class IcgSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ICGSETTINGID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ICGSETTINGID_GENERATOR")
	@Column(name="setting_id")
	private Integer settingId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="setting_description")
	private String settingDescription;

	@Column(name="setting_name")
	private String settingName;

	@Column(name="setting_status")
	private Boolean settingStatus;

	@Column(name="setting_value")
	private String settingValue;

	/*
	 * //bi-directional many-to-one association to BatchMaster
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="batch_id") private BatchMaster batchMaster;
	 */
	
	@Column(name="start_datetime")
	private Timestamp startDateTime;
	
	@Column(name="end_datetime")
	private Timestamp endDateTime;
	
	
	// bi-directional many-to-one association to BatchMaster

	@ManyToOne
	@JoinColumn(name = "exam_id")
	private ExamMaster examMaster;

	public IcgSetting() {
	}

	public Integer getSettingId() {
		return this.settingId;
	}

	public void setSettingId(Integer settingId) {
		this.settingId = settingId;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getSettingDescription() {
		return this.settingDescription;
	}

	public void setSettingDescription(String settingDescription) {
		this.settingDescription = settingDescription;
	}

	public String getSettingName() {
		return this.settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public Boolean getSettingStatus() {
		return this.settingStatus;
	}

	public void setSettingStatus(Boolean settingStatus) {
		this.settingStatus = settingStatus;
	}

	public String getSettingValue() {
		return this.settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

	/*
	 * public BatchMaster getBatchMaster() { return this.batchMaster; }
	 * 
	 * public void setBatchMaster(BatchMaster batchMaster) { this.batchMaster =
	 * batchMaster; }
	 */

	public Timestamp getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Timestamp getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	
	public ExamMaster getExamMaster() {
		return examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}
	 

}