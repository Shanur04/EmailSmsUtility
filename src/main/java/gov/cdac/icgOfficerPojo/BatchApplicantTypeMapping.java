package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the batch_applicant_type_mapping database table.
 * 
 */
@Entity
@Table(name="batch_applicant_type_mapping")
@NamedQuery(name="BatchApplicantTypeMapping.findAll", query="SELECT b FROM BatchApplicantTypeMapping b")
public class BatchApplicantTypeMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BATCH_APPLICANT_TYPE_MAPPING_BATCHAPPLICANTTYPEMAPPINGID_GENERATOR",allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BATCH_APPLICANT_TYPE_MAPPING_BATCHAPPLICANTTYPEMAPPINGID_GENERATOR")
	@Column(name="batch_applicant_type_mapping_id")
	private Integer batchApplicantTypeMappingId;

	@Column(name="batch_applicant_type_mapping_status")
	private Boolean batchApplicantTypeMappingStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantType
	@ManyToOne
	@JoinColumn(name="applicant_type_id")
	private ApplicantType applicantType;

	//bi-directional many-to-one association to BatchMaster
	@ManyToOne
	@JoinColumn(name="batch_id")
	private BatchMaster batchMaster;

	public BatchApplicantTypeMapping() {
	}

	public Integer getBatchApplicantTypeMappingId() {
		return this.batchApplicantTypeMappingId;
	}

	public void setBatchApplicantTypeMappingId(Integer batchApplicantTypeMappingId) {
		this.batchApplicantTypeMappingId = batchApplicantTypeMappingId;
	}

	public Boolean getBatchApplicantTypeMappingStatus() {
		return this.batchApplicantTypeMappingStatus;
	}

	public void setBatchApplicantTypeMappingStatus(Boolean batchApplicantTypeMappingStatus) {
		this.batchApplicantTypeMappingStatus = batchApplicantTypeMappingStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public ApplicantType getApplicantType() {
		return this.applicantType;
	}

	public void setApplicantType(ApplicantType applicantType) {
		this.applicantType = applicantType;
	}

	public BatchMaster getBatchMaster() {
		return this.batchMaster;
	}

	public void setBatchMaster(BatchMaster batchMaster) {
		this.batchMaster = batchMaster;
	}

}