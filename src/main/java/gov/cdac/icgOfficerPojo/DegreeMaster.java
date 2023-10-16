package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the degree_master database table.
 * 
 */
@Entity
@Table(name="degree_master")
@NamedQuery(name="DegreeMaster.findAll", query="SELECT d FROM DegreeMaster d")
@JsonIgnoreProperties({"batchPostDegreeStreamMappings"})
public class DegreeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEGREE_MASTER_DEGREEID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEGREE_MASTER_DEGREEID_GENERATOR")
	@Column(name="degree_id")
	private Integer degreeId;
	
	@NotBlank(message = "Degree Alias cannot be blank")
	@Column(name="degree_alias")
	private String degreeAlias;
	
	@NotBlank(message = "Degree Name cannot be blank")
	@Column(name="degree_name")
	private String degreeName;
	
	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	@NotNull(message = "Degree Master Status cannot be blank" )
	@Column(name="status")
	private Boolean status;

	//bi-directional many-to-one association to BatchPostDegreeStreamMapping
	@OneToMany(mappedBy="degreeMaster")
	private List<BatchPostDegreeStreamMapping> batchPostDegreeStreamMappings;

	//bi-directional many-to-one association to EducationLevel
	@ManyToOne
	@JoinColumn(name="education_level_id")
	private EducationLevel educationLevel;

	public DegreeMaster() {
	}
	

	public DegreeMaster(Integer degreeId, String degreeAlias, String degreeName) {
		this.degreeId = degreeId;
		this.degreeAlias = degreeAlias;
		this.degreeName = degreeName;
	}


	public Integer getDegreeId() {
		return this.degreeId;
	}

	public void setDegreeId(Integer degreeId) {
		this.degreeId = degreeId;
	}

	public String getDegreeAlias() {
		return this.degreeAlias;
	}

	public void setDegreeAlias(String degreeAlias) {
		this.degreeAlias = degreeAlias;
	}

	public String getDegreeName() {
		return this.degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<BatchPostDegreeStreamMapping> getBatchPostDegreeStreamMappings() {
		return this.batchPostDegreeStreamMappings;
	}

	public void setBatchPostDegreeStreamMappings(List<BatchPostDegreeStreamMapping> batchPostDegreeStreamMappings) {
		this.batchPostDegreeStreamMappings = batchPostDegreeStreamMappings;
	}

	public BatchPostDegreeStreamMapping addBatchPostDegreeStreamMapping(BatchPostDegreeStreamMapping batchPostDegreeStreamMapping) {
		getBatchPostDegreeStreamMappings().add(batchPostDegreeStreamMapping);
		batchPostDegreeStreamMapping.setDegreeMaster(this);

		return batchPostDegreeStreamMapping;
	}

	public BatchPostDegreeStreamMapping removeBatchPostDegreeStreamMapping(BatchPostDegreeStreamMapping batchPostDegreeStreamMapping) {
		getBatchPostDegreeStreamMappings().remove(batchPostDegreeStreamMapping);
		batchPostDegreeStreamMapping.setDegreeMaster(null);

		return batchPostDegreeStreamMapping;
	}

	public EducationLevel getEducationLevel() {
		return this.educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	@Override
	public String toString() {
		return "DegreeMaster [degreeId=" + degreeId + ", degreeAlias=" + degreeAlias + ", degreeName=" + degreeName
				+ ", recordTracking=" + recordTracking + ", status=" + status + ", batchPostDegreeStreamMappings="
				+ batchPostDegreeStreamMappings + ", educationLevel=" + educationLevel + "]";
	}

	
}