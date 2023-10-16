package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the batch_post_degree_stream_mapping database table.
 * 
 */
@Entity
@Table(name="batch_post_degree_stream_mapping")
@NamedQuery(name="BatchPostDegreeStreamMapping.findAll", query="SELECT b FROM BatchPostDegreeStreamMapping b")
@JsonIgnoreProperties({"qualificationMasters"})
public class BatchPostDegreeStreamMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BATCH_POST_DEGREE_STREAM_MAPPING_BATCHPOSTDEGREESTREAMMAPPINGID_GENERATOR",allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BATCH_POST_DEGREE_STREAM_MAPPING_BATCHPOSTDEGREESTREAMMAPPINGID_GENERATOR")
	@Column(name="batch_post_degree_stream_mapping_id")
	private Integer batchPostDegreeStreamMappingId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	private Boolean status;

	//bi-directional many-to-one association to BatchPostMapping
	@ManyToOne
	@JoinColumn(name="batch_post_mapping_id")
	private BatchPostMapping batchPostMapping;

	//bi-directional many-to-one association to DegreeMaster
	@ManyToOne
	@JoinColumn(name="degree_id")
	private DegreeMaster degreeMaster;

	//bi-directional many-to-one association to StreamMaster
	@ManyToOne
	@JoinColumn(name="stream_id")
	private StreamMaster streamMaster;

	//bi-directional many-to-one association to QualificationMaster
	@OneToMany(mappedBy="batchPostDegreeStreamMapping")
	private List<QualificationMaster> qualificationMasters;

	public BatchPostDegreeStreamMapping() {
	}

	public BatchPostDegreeStreamMapping(int batchPostDegreeStreamMappingId) {
		super();
		this.batchPostDegreeStreamMappingId=batchPostDegreeStreamMappingId;
		}

	public Integer getBatchPostDegreeStreamMappingId() {
		return this.batchPostDegreeStreamMappingId;
	}

	public void setBatchPostDegreeStreamMappingId(Integer batchPostDegreeStreamMappingId) {
		this.batchPostDegreeStreamMappingId = batchPostDegreeStreamMappingId;
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

	public BatchPostMapping getBatchPostMapping() {
		return this.batchPostMapping;
	}

	public void setBatchPostMapping(BatchPostMapping batchPostMapping) {
		this.batchPostMapping = batchPostMapping;
	}

	public DegreeMaster getDegreeMaster() {
		return this.degreeMaster;
	}

	public void setDegreeMaster(DegreeMaster degreeMaster) {
		this.degreeMaster = degreeMaster;
	}

	public StreamMaster getStreamMaster() {
		return this.streamMaster;
	}

	public void setStreamMaster(StreamMaster streamMaster) {
		this.streamMaster = streamMaster;
	}

	public List<QualificationMaster> getQualificationMasters() {
		return this.qualificationMasters;
	}

	public void setQualificationMasters(List<QualificationMaster> qualificationMasters) {
		this.qualificationMasters = qualificationMasters;
	}

	public QualificationMaster addQualificationMaster(QualificationMaster qualificationMaster) {
		getQualificationMasters().add(qualificationMaster);
		qualificationMaster.setBatchPostDegreeStreamMapping(this);

		return qualificationMaster;
	}

	public QualificationMaster removeQualificationMaster(QualificationMaster qualificationMaster) {
		getQualificationMasters().remove(qualificationMaster);
		qualificationMaster.setBatchPostDegreeStreamMapping(null);

		return qualificationMaster;
	}

}