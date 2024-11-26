package gov.cdac.icgPojo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

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
	
	public PhotoIdentityCardTypeMaster() {
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
			
}
