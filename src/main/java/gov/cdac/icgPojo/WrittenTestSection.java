package gov.cdac.icgPojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author kunalm AND ankitas 
 *
 */
/**
 * The persistent class for the written_test_section database table.
 * 
 */
@Entity
@Table(name = "written_test_section")
@NamedQuery(name = "WrittenTestSection.findAll", query = "SELECT w FROM WrittenTestSection w")
@JsonIgnoreProperties({ "icgPostCombinations" })
public class WrittenTestSection implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "WRITTENTESTSECTIONID_GENERATOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WRITTENTESTSECTIONID_GENERATOR")
    @Column(name = "written_test_section_id")
    private Integer writtenTestSectionId;

    @Column(name = "written_test")
    private String writtenTest;

    @Column(name = "written_test_section_alias")
    private String writtenTestSectionAlias;

    // bi-directional many-to-one association to IcgPostCombination
    @OneToMany(mappedBy = "writtenTestSection")
    private List<IcgPostCombination> icgPostCombinations;

    public WrittenTestSection() {
    }

    public Integer getWrittenTestSectionId() {
	return this.writtenTestSectionId;
    }

    public void setWrittenTestSectionId(Integer writtenTestSectionId) {
	this.writtenTestSectionId = writtenTestSectionId;
    }

    public String getWrittenTest() {
	return this.writtenTest;
    }

    public void setWrittenTest(String writtenTest) {
	this.writtenTest = writtenTest;
    }

    public String getWrittenTestSectionAlias() {
	return this.writtenTestSectionAlias;
    }

    public void setWrittenTestSectionAlias(String writtenTestSectionAlias) {
	this.writtenTestSectionAlias = writtenTestSectionAlias;
    }

    public List<IcgPostCombination> getIcgPostCombinations() {
	return this.icgPostCombinations;
    }

    public void setIcgPostCombinations(List<IcgPostCombination> icgPostCombinations) {
	this.icgPostCombinations = icgPostCombinations;
    }

    public IcgPostCombination addIcgPostCombination(IcgPostCombination icgPostCombination) {
	getIcgPostCombinations().add(icgPostCombination);
	icgPostCombination.setWrittenTestSection(this);

	return icgPostCombination;
    }

    public IcgPostCombination removeIcgPostCombination(IcgPostCombination icgPostCombination) {
	getIcgPostCombinations().remove(icgPostCombination);
	icgPostCombination.setWrittenTestSection(null);

	return icgPostCombination;
    }
}