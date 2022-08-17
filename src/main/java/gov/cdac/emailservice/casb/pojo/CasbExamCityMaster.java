package gov.cdac.emailservice.casb.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
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
 * The persistent class for the exam_city_master database table.
 * 
 */
@Entity
@Table(name = "exam_city_master")
@JsonIgnoreProperties(value = { "applicantExamcityPreferences" })
public class CasbExamCityMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAM_CITY_MASTER_CITYID_GENERATOR")
    @SequenceGenerator(name = "EXAM_CITY_MASTER_CITYID_GENERATOR", sequenceName = "exam_city_master_city_id_seq", allocationSize = 1)
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_alias")
    private String cityAlias;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "city_isactive")
    private Boolean cityIsactive;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    @Column(name = "region_id")
    private Integer regionId;

    public CasbExamCityMaster() {
    }

    public Integer getCityId() {
	return this.cityId;
    }

    public void setCityId(Integer cityId) {
	this.cityId = cityId;
    }

    public String getCityAlias() {
	return this.cityAlias;
    }

    public void setCityAlias(String cityAlias) {
	this.cityAlias = cityAlias;
    }

    public String getCityCode() {
	return this.cityCode;
    }

    public void setCityCode(String cityCode) {
	this.cityCode = cityCode;
    }

    public Boolean getCityIsactive() {
	return this.cityIsactive;
    }

    public void setCityIsactive(Boolean cityIsactive) {
	this.cityIsactive = cityIsactive;
    }

    public String getCityName() {
	return this.cityName;
    }

    public void setCityName(String cityName) {
	this.cityName = cityName;
    }

    public Timestamp getRecordTracking() {
	return this.recordTracking;
    }

    public void setRecordTracking(Timestamp recordTracking) {
	this.recordTracking = recordTracking;
    }

    public Integer getRegionId() {
	return this.regionId;
    }

    public void setRegionId(Integer regionId) {
	this.regionId = regionId;
    }

}