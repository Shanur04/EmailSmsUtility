package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


/**
 * The persistent class for the recruitment_zone_state database table.
 * 
 */
@Entity
@Table(name="recruitment_zone_state")
@NamedQuery(name="RecruitmentZoneState.findAll", query="SELECT r FROM RecruitmentZoneState r")
@JsonIgnoreProperties({"personalDetailsTradePostMappings","communicationDetails"})
public class RecruitmentZoneState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RECRUITMENTZONESTATEID_GENERATOR",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECRUITMENTZONESTATEID_GENERATOR")
	@Column(name="recruitment_zone_state_id")
	private Integer recruitmentZoneStateId;

	@Column(name="state_name")
	private String stateName;

	
	/*
	 * //bi-directional many-to-one association to DomicileStateMaster
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="domicile_state_id") private DomicileStateMaster
	 * domicileStateMaster;
	 */
	//bi-directional many-to-one association to RecruitmentZone
	@ManyToOne
	@JoinColumn(name="recruitment_zone_id")
	private RecruitmentZone recruitmentZone;
	
	@NotNull(message = "Recruitment Zone State Status cannot be blank" )
	@Column(name="status")
	private Boolean recruitmentZoneStateStatus;
	
	@Column(name="is_height_relaxation_applicable")
	private Boolean isHeightRelaxationApplicable;

	//bi-directional many-to-one association to CentreUserMapping
	@OneToMany(mappedBy="recruitmentZoneState")
	private List<CommunicationDetail> communicationDetails;
	
	public RecruitmentZoneState() {
	}

	public Boolean getIsHeightRelaxationApplicable() {
		return isHeightRelaxationApplicable;
	}

	public void setIsHeightRelaxationApplicable(Boolean isHeightRelaxationApplicable) {
		this.isHeightRelaxationApplicable = isHeightRelaxationApplicable;
	}

	public List<CommunicationDetail> getCommunicationDetails() {
		return communicationDetails;
	}

	public void setCommunicationDetails(List<CommunicationDetail> communicationDetails) {
		this.communicationDetails = communicationDetails;
	}

	public Boolean getRecruitmentZoneStateStatus() {
		return recruitmentZoneStateStatus;
	}

	public void setRecruitmentZoneStateStatus(Boolean recruitmentZoneStateStatus) {
		this.recruitmentZoneStateStatus = recruitmentZoneStateStatus;
	}

	public Integer getRecruitmentZoneStateId() {
		return this.recruitmentZoneStateId;
	}

	public void setRecruitmentZoneStateId(Integer recruitmentZoneStateId) {
		this.recruitmentZoneStateId = recruitmentZoneStateId;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	


	/*
	 * public DomicileStateMaster getDomicileStateMaster() { return
	 * this.domicileStateMaster; }
	 * 
	 * public void setDomicileStateMaster(DomicileStateMaster domicileStateMaster) {
	 * this.domicileStateMaster = domicileStateMaster; }
	 */
	public RecruitmentZone getRecruitmentZone() {
		return this.recruitmentZone;
	}

	public void setRecruitmentZone(RecruitmentZone recruitmentZone) {
		this.recruitmentZone = recruitmentZone;
	}

}