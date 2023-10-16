package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the castewise_postwise_dobrange_mapping database table.
 * 
 */
@Entity
@Table(name="castewise_postwise_dobrange_mapping")
@NamedQuery(name="CastewisePostwiseDobrangeMapping.findAll", query="SELECT c FROM CastewisePostwiseDobrangeMapping c")
public class CastewisePostwiseDobrangeMapping implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="CASTEWISEPOSTWISEDOBRANGEMAPPINGID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASTEWISEPOSTWISEDOBRANGEMAPPINGID_GENERATOR")
	@Column(name="castewise_postwise_dobrange_mapping_id")
	private Integer castewisePostwiseDobrangeMappingId;

	@Column(name="dob_end_date")
	private Timestamp dobEndDate;

	@Column(name="dob_start_date")
	private Timestamp dobStartDate;

	@Column(name="min_graduation_percentage")
	private Integer minGraduationPercentage;

	@Column(name="min_hsc_percentage")
	private Integer minHscPercentage;
	
	@Column(name="min_diploma_percentage")
	private Integer minDiplomaPercentage;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	
	//bi-directional many-to-one association to CasteCategoryDetail
	@ManyToOne
	@JoinColumn(name="category_id")
	private CasteCategoryDetail casteCategoryDetail;

	//bi-directional many-to-one association to IcgPost
	@ManyToOne
	@JoinColumn(name="post_id")
	private IcgPost icgPost;

	public CastewisePostwiseDobrangeMapping() {
	}

	
	public Integer getMinDiplomaPercentage() {
		return minDiplomaPercentage;
	}


	public void setMinDiplomaPercentage(Integer minDiplomaPercentage) {
		this.minDiplomaPercentage = minDiplomaPercentage;
	}


	public Integer getCastewisePostwiseDobrangeMappingId() {
		return this.castewisePostwiseDobrangeMappingId;
	}

	public void setCastewisePostwiseDobrangeMappingId(Integer castewisePostwiseDobrangeMappingId) {
		this.castewisePostwiseDobrangeMappingId = castewisePostwiseDobrangeMappingId;
	}

	public Timestamp getDobEndDate() {
		return this.dobEndDate;
	}

	public void setDobEndDate(Timestamp dobEndDate) {
		this.dobEndDate = dobEndDate;
	}

	public Timestamp getDobStartDate() {
		return this.dobStartDate;
	}

	public void setDobStartDate(Timestamp dobStartDate) {
		this.dobStartDate = dobStartDate;
	}

	public Integer getMinGraduationPercentage() {
		return this.minGraduationPercentage;
	}

	public void setMinGraduationPercentage(Integer minGraduationPercentage) {
		this.minGraduationPercentage = minGraduationPercentage;
	}

	public Integer getMinHscPercentage() {
		return this.minHscPercentage;
	}

	public void setMinHscPercentage(Integer minHscPercentage) {
		this.minHscPercentage = minHscPercentage;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	

	public CasteCategoryDetail getCasteCategoryDetail() {
		return this.casteCategoryDetail;
	}

	public void setCasteCategoryDetail(CasteCategoryDetail casteCategoryDetail) {
		this.casteCategoryDetail = casteCategoryDetail;
	}

	public IcgPost getIcgPost() {
		return this.icgPost;
	}

	public void setIcgPost(IcgPost icgPost) {
		this.icgPost = icgPost;
	}

}