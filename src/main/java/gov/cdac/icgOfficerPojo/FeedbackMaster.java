package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the feedback_master database table.
 * 
 */
@Entity
@Table(name="feedback_master")
@NamedQuery(name="FeedbackMaster.findAll", query="SELECT f FROM FeedbackMaster f")
@JsonIgnoreProperties({"feedbackResponses"})
public class FeedbackMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FEEDBACK_MASTER_FEEDBACKMASTERID_GENERATOR",allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FEEDBACK_MASTER_FEEDBACKMASTERID_GENERATOR")
	@Column(name="feedback_master_id")
	private Integer feedbackMasterId;

	private String category;

	@Column(name="display_order")
	private Integer displayOrder;

	@Column(name="is_subq")
	private Integer isSubq;

	private String options;

	private String question;

	private String type;

	//bi-directional many-to-one association to FeedbackRespons
	@OneToMany(mappedBy="feedbackMaster")
	private List<FeedbackRespons> feedbackResponses;

	public FeedbackMaster() {
	}

	public Integer getFeedbackMasterId() {
		return this.feedbackMasterId;
	}

	public void setFeedbackMasterId(Integer feedbackMasterId) {
		this.feedbackMasterId = feedbackMasterId;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getIsSubq() {
		return this.isSubq;
	}

	public void setIsSubq(Integer isSubq) {
		this.isSubq = isSubq;
	}

	public String getOptions() {
		return this.options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FeedbackRespons> getFeedbackResponses() {
		return this.feedbackResponses;
	}

	public void setFeedbackResponses(List<FeedbackRespons> feedbackResponses) {
		this.feedbackResponses = feedbackResponses;
	}

	public FeedbackRespons addFeedbackRespons(FeedbackRespons feedbackRespons) {
		getFeedbackResponses().add(feedbackRespons);
		feedbackRespons.setFeedbackMaster(this);

		return feedbackRespons;
	}

	public FeedbackRespons removeFeedbackRespons(FeedbackRespons feedbackRespons) {
		getFeedbackResponses().remove(feedbackRespons);
		feedbackRespons.setFeedbackMaster(null);

		return feedbackRespons;
	}

}