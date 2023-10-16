package gov.cdac.casbPojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the centre_details database table.
 * 
 */
@Entity
@Table(name = "center_master")
public class CentreDetail implements Serializable {
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

    public CentreDetail() {
    }

    public CentreDetail(Integer centreId, String code, String name, Boolean status) {
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