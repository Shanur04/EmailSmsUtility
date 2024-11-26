package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the caste_category_master database table.
 * 
 */
@Entity
@Table(name="caste_category_master")
@NamedQuery(name="CasteCategoryMaster.findAll", query="SELECT c FROM CasteCategoryMaster c")
@JsonIgnoreProperties({"casteCategoryDetails"})
public class CasteCategoryMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CASTECATEGORYMASTERID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASTECATEGORYMASTERID_GENERATOR")
	@Column(name="caste_category_master_id")
	private Integer casteCategoryMasterId;

	@Column(name = "category")
	private String category;

	@Column(name="category_code")
	private String categoryCode;
	
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="category_long_code")
	private String categoryLongCode;

	//bi-directional many-to-one association to CasteCategoryDetail
	@OneToMany(mappedBy="casteCategoryMaster")
	private List<CasteCategoryDetail> casteCategoryDetails;

	public CasteCategoryMaster() {
	}

	public Integer getCasteCategoryMasterId() {
		return this.casteCategoryMasterId;
	}

	public void setCasteCategoryMasterId(Integer casteCategoryMasterId) {
		this.casteCategoryMasterId = casteCategoryMasterId;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public Integer getCategoryId() {
	    return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
	    this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public List<CasteCategoryDetail> getCasteCategoryDetails() {
		return this.casteCategoryDetails;
	}

	public void setCasteCategoryDetails(List<CasteCategoryDetail> casteCategoryDetails) {
		this.casteCategoryDetails = casteCategoryDetails;
	}

	public String getCategoryLongCode() {
	    return categoryLongCode;
	}

	public void setCategoryLongCode(String categoryLongCode) {
	    this.categoryLongCode = categoryLongCode;
	}

	public CasteCategoryDetail addCasteCategoryDetail(CasteCategoryDetail casteCategoryDetail) {
		getCasteCategoryDetails().add(casteCategoryDetail);
		casteCategoryDetail.setCasteCategoryMaster(this);

		return casteCategoryDetail;
	}

	public CasteCategoryDetail removeCasteCategoryDetail(CasteCategoryDetail casteCategoryDetail) {
		getCasteCategoryDetails().remove(casteCategoryDetail);
		casteCategoryDetail.setCasteCategoryMaster(null);

		return casteCategoryDetail;
	}

}