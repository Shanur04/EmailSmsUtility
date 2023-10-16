package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the domicile_state_master database table.
 * 
 */
@Entity
@Table(name="domicile_state_master")
@NamedQuery(name="DomicileStateMaster.findAll", query="SELECT d FROM DomicileStateMaster d")
public class DomicileStateMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOMICILESTATEID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOMICILESTATEID_GENERATOR")
	@Column(name="domicile_state_id")
	private Integer domicileStateId;

	@Column(name="domicile_state")
	private String domicileState;

	public DomicileStateMaster() {
	}

	public Integer getDomicileStateId() {
		return this.domicileStateId;
	}

	public void setDomicileStateId(Integer domicileStateId) {
		this.domicileStateId = domicileStateId;
	}

	public String getDomicileState() {
		return this.domicileState;
	}

	public void setDomicileState(String domicileState) {
		this.domicileState = domicileState;
	}


}