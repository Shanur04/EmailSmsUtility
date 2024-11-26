package gov.cdac.casbPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the casb_group database table.
 * 
 */
@Entity
@Table(name = "casb_group")
@JsonIgnoreProperties(value = { "casbGroupSubjects", "examCasbGroupSlots", "personalDetails" })
public class CasbGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "casb_group_seq")
    @SequenceGenerator(name = "casb_group_seq", sequenceName = "casb_group_casb_group_id_seq", allocationSize = 1)
    @Column(name = "casb_group_id")
    private Integer casbGroupId;

    private String name;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    // bi-directional many-to-one association to ExamCasbGroupSlot
    @OneToMany(mappedBy = "casbGroup")
    private List<ExamCasbGroupSlot> examCasbGroupSlots;

    // bi-directional many-to-one association to PersonalDetail
    @OneToMany(mappedBy = "casbGroup")
    private List<CasbPersonalDetail> casbPersonalDetails;

    public CasbGroup() {
    }

    public Integer getCasbGroupId() {
	return this.casbGroupId;
    }

    public void setCasbGroupId(Integer casbGroupId) {
	this.casbGroupId = casbGroupId;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Timestamp getRecordTracking() {
	return this.recordTracking;
    }

    public void setRecordTracking(Timestamp recordTracking) {
	this.recordTracking = recordTracking;
    }

    public List<ExamCasbGroupSlot> getExamCasbGroupSlots() {
	return this.examCasbGroupSlots;
    }

    public void setExamCasbGroupSlots(List<ExamCasbGroupSlot> examCasbGroupSlots) {
	this.examCasbGroupSlots = examCasbGroupSlots;
    }

    public ExamCasbGroupSlot addExamCasbGroupSlot(ExamCasbGroupSlot examCasbGroupSlot) {
	getExamCasbGroupSlots().add(examCasbGroupSlot);
	examCasbGroupSlot.setCasbGroup(this);

	return examCasbGroupSlot;
    }

    public ExamCasbGroupSlot removeExamCasbGroupSlot(ExamCasbGroupSlot examCasbGroupSlot) {
	getExamCasbGroupSlots().remove(examCasbGroupSlot);
	examCasbGroupSlot.setCasbGroup(null);

	return examCasbGroupSlot;
    }

    public List<CasbPersonalDetail> getPersonalDetails() {
	return this.casbPersonalDetails;
    }

    public void setPersonalDetails(List<CasbPersonalDetail> casbPersonalDetails) {
	this.casbPersonalDetails = casbPersonalDetails;
    }

    public CasbPersonalDetail addPersonalDetail(CasbPersonalDetail casbPersonalDetail) {
	getPersonalDetails().add(casbPersonalDetail);
	casbPersonalDetail.setCasbGroup(this);

	return casbPersonalDetail;
    }

    public CasbPersonalDetail removePersonalDetail(CasbPersonalDetail casbPersonalDetail) {
	getPersonalDetails().remove(casbPersonalDetail);
	casbPersonalDetail.setCasbGroup(null);

	return casbPersonalDetail;
    }

}