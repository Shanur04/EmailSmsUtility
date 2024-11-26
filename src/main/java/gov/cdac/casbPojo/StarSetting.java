package gov.cdac.casbPojo;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * The persistent class for the star_settings database table.
 * 
 */
@Entity
@Table(name = "star_settings")
public class StarSetting implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id")
    private Long settingId;

    @Column(name = "inserted_at")
    private Timestamp insertedAt;

    @Column(name = "last_updated_at")
    private Timestamp lastUpdatedAt;

    @Column(name = "setting_description")
    private String settingDescription;

    @Column(name = "setting_name")
    private String settingName;

    @Column(name = "setting_value")
    private String settingValue;

    /*
     * @Column(name="start_date_time") private Timestamp startDateTime;
     * 
     * @Column(name="end_date_time") private Timestamp endDateTime;
     * 
     * @Column(name="last_date_time_of_payment") private Timestamp
     * lastDateTimeOfPayment;
     */

    public StarSetting() {
    }

    public Long getSettingId() {
	return this.settingId;
    }

    public void setSettingId(Long settingId) {
	this.settingId = settingId;
    }

    public Timestamp getInsertedAt() {
	return this.insertedAt;
    }

    public void setInsertedAt(Timestamp insertedAt) {
	this.insertedAt = insertedAt;
    }

    public Timestamp getLastUpdatedAt() {
	return this.lastUpdatedAt;
    }

    public void setLastUpdatedAt(Timestamp lastUpdatedAt) {
	this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getSettingDescription() {
	return this.settingDescription;
    }

    public void setSettingDescription(String settingDescription) {
	this.settingDescription = settingDescription;
    }

    public String getSettingName() {
	return this.settingName;
    }

    public void setSettingName(String settingName) {
	this.settingName = settingName;
    }

    public String getSettingValue() {
	return this.settingValue;
    }

    public void setSettingValue(String settingValue) {
	this.settingValue = settingValue;
    }

    /*
     * public Timestamp getStartDateTime() { return startDateTime; }
     * 
     * public void setStartDateTime(Timestamp startDateTime) { this.startDateTime =
     * startDateTime; }
     * 
     * public Timestamp getEndDateTime() { return endDateTime; }
     * 
     * public void setEndDateTime(Timestamp endDateTime) { this.endDateTime =
     * endDateTime; }
     * 
     * public Timestamp getLastDateTimeOfPayment() { return lastDateTimeOfPayment; }
     * 
     * public void setLastDateTimeOfPayment(Timestamp lastDateTimeOfPayment) {
     * this.lastDateTimeOfPayment = lastDateTimeOfPayment; }
     */

}