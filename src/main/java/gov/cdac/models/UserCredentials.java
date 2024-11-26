package gov.cdac.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserCredentials extends User  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long appCredId;
	Integer systemUserId;

	public UserCredentials(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,Long appCredId,Integer systemUserId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.appCredId = appCredId;
		this.systemUserId = systemUserId;
	}
		
	public Long getAppCredId() {
		return appCredId;
	}

	public void setAppCredId(Long appCredId) {
		this.appCredId = appCredId;
	}
	
	public Integer getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Integer systemUserId) {
		this.systemUserId = systemUserId;
	}

}
	