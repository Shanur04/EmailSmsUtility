package gov.cdac.emailservice.icg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author kunalm AND ankitas 
 *
 */

/**
 * The persistent class for the exam_master database table.
 * 
 */
@Entity
@Table(name = "exam_master")
@NamedQuery(name = "ExamMaster.findAll", query = "SELECT e FROM ExamMaster e")
@JsonIgnoreProperties({ "applicantCentreAllotments", "centreMasters", "examSlots", "examSlotwiseReportingtimes",
	"regionMasters"})
public class ExamMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "EXAMID_GENERATOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAMID_GENERATOR")
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

    // bi-directional many-to-one association to ApplicantCentreAllotment
    @OneToMany(mappedBy = "examMaster")
    private List<ApplicantCentreAllotment> applicantCentreAllotments;
    
	@JsonIgnore
	@OneToMany(mappedBy="exam")
	private List<IcgEmailSent> emailSent;

    // bi-directional many-to-one association to CentreMaster
    @OneToMany(mappedBy = "examMaster")
    private List<CentreMaster> centreMasters;

    // bi-directional many-to-one association to ExamSlot
    @OneToMany(mappedBy = "examMaster")
    private List<ExamSlot> examSlots;

    // bi-directional many-to-one association to RegionMaster
    @OneToMany(mappedBy = "examMaster")
    private List<RegionMaster> regionMasters;
    
    //bi-directional many-to-one association to BatchMaster
  	@ManyToOne
  	@JoinColumn(name="batch_id")
  	private BatchMaster batchMaster;
  	
  	@ManyToOne
  	@JoinColumn(name="exam_type_master_id")
  	private ExamTypeMaster examTypeMaster;

    public ExamMaster() {
    }
     
    public ExamMaster(Integer examId) {
		super();
		this.examId = examId;
	}
    
    public List<IcgEmailSent> getEmailSent() {
		return emailSent;
	}

	public void setEmailSent(List<IcgEmailSent> emailSent) {
		this.emailSent = emailSent;
	}

	public ExamTypeMaster getExamTypeMaster() {
		return examTypeMaster;
	}

	public void setExamTypeMaster(ExamTypeMaster examTypeMaster) {
		this.examTypeMaster = examTypeMaster;
	}

    public Integer getExamId() {
	return this.examId;
    }

    public void setExamId(Integer examId) {
	this.examId = examId;
    }

    public Integer getExamDuration() {
	return this.examDuration;
    }

    public void setExamDuration(Integer examDuration) {
	this.examDuration = examDuration;
    }

    public String getExamMonth() {
	return this.examMonth;
    }

    public void setExamMonth(String examMonth) {
	this.examMonth = examMonth;
    }

    public String getExamName() {
	return this.examName;
    }

    public void setExamName(String examName) {
	this.examName = examName;
    }

    public Boolean getExamStatus() {
	return this.examStatus;
    }

    public void setExamStatus(Boolean examStatus) {
	this.examStatus = examStatus;
    }

    public String getExamYear() {
	return this.examYear;
    }

    public void setExamYear(String examYear) {
	this.examYear = examYear;
    }

    public Timestamp getRecordTracking() {
	return this.recordTracking;
    }

    public void setRecordTracking(Timestamp recordTracking) {
	this.recordTracking = recordTracking;
    }

    public List<ApplicantCentreAllotment> getApplicantCentreAllotments() {
	return this.applicantCentreAllotments;
    }

    public void setApplicantCentreAllotments(List<ApplicantCentreAllotment> applicantCentreAllotments) {
	this.applicantCentreAllotments = applicantCentreAllotments;
    }

    public List<ExamSlot> getExamSlots() {
	return this.examSlots;
    }

    public void setExamSlots(List<ExamSlot> examSlots) {
	this.examSlots = examSlots;
    }

    public List<RegionMaster> getRegionMasters() {
	return this.regionMasters;
    }

    public void setRegionMasters(List<RegionMaster> regionMasters) {
	this.regionMasters = regionMasters;
    }

    public RegionMaster addRegionMaster(RegionMaster regionMaster) {
	getRegionMasters().add(regionMaster);
	regionMaster.setExamMaster(this);

	return regionMaster;
    }

    public RegionMaster removeRegionMaster(RegionMaster regionMaster) {
	getRegionMasters().remove(regionMaster);
	regionMaster.setExamMaster(null);

	return regionMaster;
    }

	public BatchMaster getBatchMaster() {
		return batchMaster;
	}

	public void setBatchMaster(BatchMaster batchMaster) {
		this.batchMaster = batchMaster;
	}
}