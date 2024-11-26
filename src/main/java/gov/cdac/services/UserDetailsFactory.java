package gov.cdac.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsFactory {

	private UserDetailsServiceImpl userDetailsServiceImpl;

	public UserDetailsFactory(@Qualifier("icgUserDetails") UserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	/**
	 * Calls getPageData according to reqType
	 * 
	 * @param reqType
	 * @return
	 */
	public UserDetails getUserDetails(String username) {
		return userDetailsServiceImpl.loadUserByUsername(username);
	}
}
