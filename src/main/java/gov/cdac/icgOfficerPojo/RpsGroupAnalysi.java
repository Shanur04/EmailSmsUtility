package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rps_group_analysis database table.
 * 
 */
@Entity
@Table(name="rps_group_analysis")
@NamedQuery(name="RpsGroupAnalysi.findAll", query="SELECT r FROM RpsGroupAnalysi r")
public class RpsGroupAnalysi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="centre_code", length=255)
	private String centreCode;

	@Column(length=255)
	private String city;

	@Column(name="exam_category", length=255)
	private String examCategory;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="passed_count")
	private Long passedCount;

	@Column(name="passed_percentage")
	private float passedPercentage;

	@Column(length=255)
	private String region;

	@Column(name="slot_code", length=255)
	private String slotCode;

	@Column(length=255)
	private String state;

	@Column(name="total_candidates_appeared")
	private Long totalCandidatesAppeared;

	@Column(name="total_candidates_called")
	private Long totalCandidatesCalled;

	public RpsGroupAnalysi() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCentreCode() {
		return this.centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getExamCategory() {
		return this.examCategory;
	}

	public void setExamCategory(String examCategory) {
		this.examCategory = examCategory;
	}

	public String getExamCode() {
		return this.examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public Long getPassedCount() {
		return this.passedCount;
	}

	public void setPassedCount(Long passedCount) {
		this.passedCount = passedCount;
	}

	public float getPassedPercentage() {
		return this.passedPercentage;
	}

	public void setPassedPercentage(float passedPercentage) {
		this.passedPercentage = passedPercentage;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSlotCode() {
		return this.slotCode;
	}

	public void setSlotCode(String slotCode) {
		this.slotCode = slotCode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getTotalCandidatesAppeared() {
		return this.totalCandidatesAppeared;
	}

	public void setTotalCandidatesAppeared(Long totalCandidatesAppeared) {
		this.totalCandidatesAppeared = totalCandidatesAppeared;
	}

	public Long getTotalCandidatesCalled() {
		return this.totalCandidatesCalled;
	}

	public void setTotalCandidatesCalled(Long totalCandidatesCalled) {
		this.totalCandidatesCalled = totalCandidatesCalled;
	}

}