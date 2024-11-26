package gov.cdac.icgPojo;

import java.io.Serializable;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author kunalm AND ankitas 
 *
 */
/**
 * The persistent class for the icg_post_combination database table.
 * 
 */
@Entity
@Table(name = "icg_post_combination")
@NamedQuery(name = "IcgPostCombination.findAll", query = "SELECT i FROM IcgPostCombination i")
@JsonIgnoreProperties({ "examCentreMappings" })
public class IcgPostCombination implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ICGPOSTCOMBINATIONID_GENERATOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ICGPOSTCOMBINATIONID_GENERATOR")
    @Column(name = "icg_post_combination_id")
    private Integer icgPostCombinationId;

    @Column(name = "icg_post_combination_of_posts")
    private String icgPostCombinationOfPosts;

    @Column(name = "post_combination_code")
    private String postCombinationCode;

    // bi-directional many-to-one association to WrittenTestSection
    @ManyToOne
    @JoinColumn(name = "written_test_section_id")
    private WrittenTestSection writtenTestSection;

    public String getPostCombinationCode() {
	return postCombinationCode;
    }

    public void setPostCombinationCode(String postCombinationCode) {
	this.postCombinationCode = postCombinationCode;
    }

    // bi-directional many-to-one association to ExamCentreMapping
    @OneToMany(mappedBy = "icgPostCombination")
    private List<ExamCentreMapping> examCentreMappings;

    public IcgPostCombination() {
    }

    public Integer getIcgPostCombinationId() {
	return this.icgPostCombinationId;
    }

    public void setIcgPostCombinationId(Integer icgPostCombinationId) {
	this.icgPostCombinationId = icgPostCombinationId;
    }

    public String getIcgPostCombinationOfPosts() {
	return this.icgPostCombinationOfPosts;
    }

    public void setIcgPostCombinationOfPosts(String icgPostCombinationOfPosts) {
	this.icgPostCombinationOfPosts = icgPostCombinationOfPosts;
    }

    public List<ExamCentreMapping> getExamCentreMappings() {
	return this.examCentreMappings;
    }

    public void setExamCentreMappings(List<ExamCentreMapping> examCentreMappings) {
	this.examCentreMappings = examCentreMappings;
    }

    public WrittenTestSection getWrittenTestSection() {
	return writtenTestSection;
    }

    public void setWrittenTestSection(WrittenTestSection writtenTestSection) {
	this.writtenTestSection = writtenTestSection;
    }
}