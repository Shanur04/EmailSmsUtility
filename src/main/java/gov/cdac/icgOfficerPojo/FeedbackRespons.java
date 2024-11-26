package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the feedback_responses database table.
 * 
 */
@Entity
@Table(name="feedback_responses")
@NamedQuery(name="FeedbackRespons.findAll", query="SELECT f FROM FeedbackRespons f")
public class FeedbackRespons implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FEEDBACK_RESPONSES_FEEDBACKRESPONSESID_GENERATOR" ,allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FEEDBACK_RESPONSES_FEEDBACKRESPONSESID_GENERATOR")
	@Column(name="feedback_responses_id")
	private Integer feedbackResponsesId;

	private String answer;

	//bi-directional many-to-one association to CentreMaster
	@ManyToOne
	@JoinColumn(name="centre_id")
	private CentreMaster centreMaster;

	//bi-directional many-to-one association to FeedbackMaster
	@ManyToOne
	@JoinColumn(name="feedback_master_id")
	private FeedbackMaster feedbackMaster;

	//bi-directional many-to-one association to SystemUserCredential
	@ManyToOne
	@JoinColumn(name="system_user_cred_id")
	private SystemUserCredential systemUserCredential;

	public FeedbackRespons() {
	}

	public Integer getFeedbackResponsesId() {
		return this.feedbackResponsesId;
	}

	public void setFeedbackResponsesId(Integer feedbackResponsesId) {
		this.feedbackResponsesId = feedbackResponsesId;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public CentreMaster getCentreMaster() {
		return this.centreMaster;
	}

	public void setCentreMaster(CentreMaster centreMaster) {
		this.centreMaster = centreMaster;
	}

	public FeedbackMaster getFeedbackMaster() {
		return this.feedbackMaster;
	}

	public void setFeedbackMaster(FeedbackMaster feedbackMaster) {
		this.feedbackMaster = feedbackMaster;
	}

	public SystemUserCredential getSystemUserCredential() {
		return this.systemUserCredential;
	}

	public void setSystemUserCredential(SystemUserCredential systemUserCredential) {
		this.systemUserCredential = systemUserCredential;
	}

}