package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
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

@Entity
@Table(name="photo_identity_card_type_master")
@NamedQuery(name="PhotoIdentityCardTypeMaster.findAll", query="SELECT i FROM PhotoIdentityCardTypeMaster i")
@JsonIgnoreProperties({"personalDetails"})
public class PhotoIdentityCardTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	
			@Id
			@SequenceGenerator(name="PHOTOIDENTITYCARDTYPEMASTERID_GENERATOR" , allocationSize = 1)
			@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PHOTOIDENTITYCARDTYPEMASTERID_GENERATOR")
			@Column(name="photo_identity_card_type_master_id")
			private Integer photoIdentityCardTypeMasterId;
		
			@Column(name="photo_identity_card_type")
			private String photoIdentityCardType;
	
	
			//bi-directional many-to-one association to PersonalDetail
			@OneToMany(mappedBy="photoIdentityCardTypeMaster")
			private List<PersonalDetail> personalDetails;

			public PhotoIdentityCardTypeMaster() {
			}

			public PhotoIdentityCardTypeMaster(Integer photoIdentityCardTypeMasterId, String photoIdentityCardType,
			List<PersonalDetail> personalDetails) {
				super();
				this.photoIdentityCardTypeMasterId = photoIdentityCardTypeMasterId;
				this.photoIdentityCardType = photoIdentityCardType;
				this.personalDetails = personalDetails;
			}

			
			public Integer getPhotoIdentityCardTypeMasterId() {
				return photoIdentityCardTypeMasterId;
			}

			public void setPhotoIdentityCardTypeMasterId(Integer photoIdentityCardTypeMasterId) {
				this.photoIdentityCardTypeMasterId = photoIdentityCardTypeMasterId;
			}

			public String getPhotoIdentityCardType() {
				return photoIdentityCardType;
			}

			public void setPhotoIdentityCardType(String photoIdentityCardType) {
				this.photoIdentityCardType = photoIdentityCardType;
			}

			public List<PersonalDetail> getPersonalDetails() {
				return personalDetails;
			}

			public void setPersonalDetails(List<PersonalDetail> personalDetails) {
				this.personalDetails = personalDetails;
			}

	
	
}
