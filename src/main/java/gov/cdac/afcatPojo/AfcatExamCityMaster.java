package gov.cdac.afcatPojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the exam_city_master database table.
 * 
 */
@Entity
@Table(name = "exam_city_master")
public class AfcatExamCityMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
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

    public AfcatExamCityMaster() {
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

}