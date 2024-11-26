package gov.cdac.afcatPojo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The persistent class for the centre_details database table.
 * 
 */
@Entity
@Table(name = "center_master")
public class AfcatCentreDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "center_id")
    private Integer centreId;

    @Column(name = "center_code")
    private String code;

    @Column(name = "center_name")
    private String name;

    @Column(name = "center_status")
    private Boolean status;

    public AfcatCentreDetail() {
    }

    public AfcatCentreDetail(Integer centreId, String code, String name, Boolean status) {
	super();
	this.centreId = centreId;
	this.code = code;
	this.name = name;
	this.status = status;
    }

    public Integer getCentreId() {
	return this.centreId;
    }

    public void setCentreId(Integer centreId) {
	this.centreId = centreId;
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

    public Boolean getStatus() {
	return this.status;
    }

    public void setStatus(Boolean status) {
	this.status = status;
    }

}