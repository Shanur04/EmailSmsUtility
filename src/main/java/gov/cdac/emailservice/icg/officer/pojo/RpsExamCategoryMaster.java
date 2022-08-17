package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rps_exam_category_master database table.
 * 
 */
@Entity
@Table(name="rps_exam_category_master")
@NamedQuery(name="RpsExamCategoryMaster.findAll", query="SELECT r FROM RpsExamCategoryMaster r")
public class RpsExamCategoryMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=255)
	private String code;

	@Column(nullable=false, length=255)
	private String name;

	//bi-directional many-to-one association to RpsExamCategoryMapping
	@OneToMany(mappedBy="rpsExamCategoryMaster")
	private List<RpsExamCategoryMapping> rpsExamCategoryMappings;

	//bi-directional many-to-one association to RpsExamCategorySectionMapping
	@OneToMany(mappedBy="rpsExamCategoryMaster")
	private List<RpsExamCategorySectionMapping> rpsExamCategorySectionMappings;

	public RpsExamCategoryMaster() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RpsExamCategoryMapping> getRpsExamCategoryMappings() {
		return this.rpsExamCategoryMappings;
	}

	public void setRpsExamCategoryMappings(List<RpsExamCategoryMapping> rpsExamCategoryMappings) {
		this.rpsExamCategoryMappings = rpsExamCategoryMappings;
	}

	public RpsExamCategoryMapping addRpsExamCategoryMapping(RpsExamCategoryMapping rpsExamCategoryMapping) {
		getRpsExamCategoryMappings().add(rpsExamCategoryMapping);
		rpsExamCategoryMapping.setRpsExamCategoryMaster(this);

		return rpsExamCategoryMapping;
	}

	public RpsExamCategoryMapping removeRpsExamCategoryMapping(RpsExamCategoryMapping rpsExamCategoryMapping) {
		getRpsExamCategoryMappings().remove(rpsExamCategoryMapping);
		rpsExamCategoryMapping.setRpsExamCategoryMaster(null);

		return rpsExamCategoryMapping;
	}

	public List<RpsExamCategorySectionMapping> getRpsExamCategorySectionMappings() {
		return this.rpsExamCategorySectionMappings;
	}

	public void setRpsExamCategorySectionMappings(List<RpsExamCategorySectionMapping> rpsExamCategorySectionMappings) {
		this.rpsExamCategorySectionMappings = rpsExamCategorySectionMappings;
	}

	public RpsExamCategorySectionMapping addRpsExamCategorySectionMapping(RpsExamCategorySectionMapping rpsExamCategorySectionMapping) {
		getRpsExamCategorySectionMappings().add(rpsExamCategorySectionMapping);
		rpsExamCategorySectionMapping.setRpsExamCategoryMaster(this);

		return rpsExamCategorySectionMapping;
	}

	public RpsExamCategorySectionMapping removeRpsExamCategorySectionMapping(RpsExamCategorySectionMapping rpsExamCategorySectionMapping) {
		getRpsExamCategorySectionMappings().remove(rpsExamCategorySectionMapping);
		rpsExamCategorySectionMapping.setRpsExamCategoryMaster(null);

		return rpsExamCategorySectionMapping;
	}

}