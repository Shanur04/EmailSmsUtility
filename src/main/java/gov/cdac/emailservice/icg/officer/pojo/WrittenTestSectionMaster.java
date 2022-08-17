package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the written_test_section_master database table.
 * 
 */
@Entity
@Table(name="written_test_section_master")
@NamedQuery(name="WrittenTestSectionMaster.findAll", query="SELECT w FROM WrittenTestSectionMaster w")
public class WrittenTestSectionMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="WRITTENTESTSECTIONMASTERID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WRITTENTESTSECTIONMASTERID_GENERATOR")
	@Column(name="written_test_section_master_id")
	private Integer writtenTestSectionMasterId;


	@Column(name="written_test")
	private String writtenTest;

	@Column(name="written_test_section_alias")
	private String writtenTestSectionAlias;

	@Column(name="written_test_section_code")
	private String writtesn_TestSection_code;
	
	@Column(name="written_test_section_description")
	private String writtenTestSectionDescription;

	public WrittenTestSectionMaster() {
	}

	
	
	public String getWrittenTestSectionDescription() {
		return writtenTestSectionDescription;
	}



	public void setWrittenTestSectionDescription(String writtenTestSectionDescription) {
		this.writtenTestSectionDescription = writtenTestSectionDescription;
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

	public Integer getWrittenTestSectionMasterId() {
		return this.writtenTestSectionMasterId;
	}

	public void setWrittenTestSectionMasterId(Integer writtenTestSectionMasterId) {
		this.writtenTestSectionMasterId = writtenTestSectionMasterId;
	}

	public String getWrittesn_TestSection_code() {
		return this.writtesn_TestSection_code;
	}

	public void setWrittesn_TestSection_code(String writtesn_TestSection_code) {
		this.writtesn_TestSection_code = writtesn_TestSection_code;
	}

}