package gov.cdac.afcatPojo;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "exam_master")
public class AfcatExamMaster {

    @Id
    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "exam_duration")
    private Integer examDuration;

    @Column(name = "exam_month")
    private String examMonth;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "exam_status")
    private Boolean examStatus;

    @Column(name = "exam_year")
    private String examYear;

    @UpdateTimestamp
    @Column(name = "record_tracking")
    private Timestamp recordTracking;
        
	@JsonIgnore
	@OneToMany(mappedBy="exam")
	private List<AfcatEmailSent> emailSent;

    // bi-directional many-to-one association to ExamSlot
    @OneToMany(mappedBy = "examMaster")
    private List<AfcatExamSlot> examSlots;
    
    //bi-directional many-to-one association to BatchMaster
  	@ManyToOne
  	@JoinColumn(name="batch_id")
  	private AfcatBatchDetail batchMaster;
    

    public AfcatExamMaster() {
    }

	public AfcatExamMaster(Integer afcatExamMasterId) {
		this.examId = afcatExamMasterId;
	}

	public Integer getExamId() {
		return examId;
	}


	public void setExamId(Integer examId) {
		this.examId = examId;
	}


	public Integer getExamDuration() {
		return examDuration;
	}


	public void setExamDuration(Integer examDuration) {
		this.examDuration = examDuration;
	}


	public String getExamMonth() {
		return examMonth;
	}


	public void setExamMonth(String examMonth) {
		this.examMonth = examMonth;
	}


	public String getExamName() {
		return examName;
	}


	public void setExamName(String examName) {
		this.examName = examName;
	}


	public Boolean getExamStatus() {
		return examStatus;
	}


	public void setExamStatus(Boolean examStatus) {
		this.examStatus = examStatus;
	}


	public String getExamYear() {
		return examYear;
	}


	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}


	public Timestamp getRecordTracking() {
		return recordTracking;
	}


	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}


	public List<AfcatEmailSent> getEmailSent() {
		return emailSent;
	}


	public void setEmailSent(List<AfcatEmailSent> emailSent) {
		this.emailSent = emailSent;
	}


	public List<AfcatExamSlot> getExamSlots() {
		return examSlots;
	}


	public void setExamSlots(List<AfcatExamSlot> examSlots) {
		this.examSlots = examSlots;
	}


	public AfcatBatchDetail getBatchMaster() {
		return batchMaster;
	}


	public void setBatchMaster(AfcatBatchDetail batchMaster) {
		this.batchMaster = batchMaster;
	}

}
