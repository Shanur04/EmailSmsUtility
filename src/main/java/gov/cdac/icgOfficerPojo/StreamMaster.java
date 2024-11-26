package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * The persistent class for the stream_master database table.
 * 
 */
@Entity
@Table(name="stream_master")
@NamedQuery(name="StreamMaster.findAll", query="SELECT s FROM StreamMaster s")
@JsonIgnoreProperties({"batchPostDegreeStreamMappings"})
public class StreamMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STREAM_MASTER_STREAMMASTERID_GENERATOR",allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STREAM_MASTER_STREAMMASTERID_GENERATOR")
	@Column(name="stream_master_id")
	private Integer streamMasterId;
	
	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	@NotNull(message = "Stream Master Status cannot be blank!" )
	@Column(name="status")
	private Boolean status;
	
	@NotBlank(message = "Stream Alias cannot be blank!")
	@Column(name="stream_alias")
	private String streamAlias;
	
	@NotBlank(message = "Stream Name cannot be blank!")
	@Column(name="stream_name")
	private String streamName;

	//bi-directional many-to-one association to BatchPostDegreeStreamMapping
	@OneToMany(mappedBy="streamMaster")
	private List<BatchPostDegreeStreamMapping> batchPostDegreeStreamMappings;

	public StreamMaster() {
	}
	
	
	

	public StreamMaster(Integer streamMasterId, String streamName) {
		this.streamMasterId = streamMasterId;
		this.streamName = streamName;
	}




	public Integer getStreamMasterId() {
		return this.streamMasterId;
	}

	public void setStreamMasterId(Integer streamMasterId) {
		this.streamMasterId = streamMasterId;
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

	public String getStreamAlias() {
		return this.streamAlias;
	}

	public void setStreamAlias(String streamAlias) {
		this.streamAlias = streamAlias;
	}

	public String getStreamName() {
		return this.streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public List<BatchPostDegreeStreamMapping> getBatchPostDegreeStreamMappings() {
		return this.batchPostDegreeStreamMappings;
	}

	public void setBatchPostDegreeStreamMappings(List<BatchPostDegreeStreamMapping> batchPostDegreeStreamMappings) {
		this.batchPostDegreeStreamMappings = batchPostDegreeStreamMappings;
	}

	public BatchPostDegreeStreamMapping addBatchPostDegreeStreamMapping(BatchPostDegreeStreamMapping batchPostDegreeStreamMapping) {
		getBatchPostDegreeStreamMappings().add(batchPostDegreeStreamMapping);
		batchPostDegreeStreamMapping.setStreamMaster(this);

		return batchPostDegreeStreamMapping;
	}

	public BatchPostDegreeStreamMapping removeBatchPostDegreeStreamMapping(BatchPostDegreeStreamMapping batchPostDegreeStreamMapping) {
		getBatchPostDegreeStreamMappings().remove(batchPostDegreeStreamMapping);
		batchPostDegreeStreamMapping.setStreamMaster(null);

		return batchPostDegreeStreamMapping;
	}

}