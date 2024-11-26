package gov.cdac.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.cdac.icgPojo.SystemUserCredential;
import gov.cdac.icgRepositories.SystemUserCredentialsRepository;
import gov.cdac.models.UserCredentials;

@Service("icgUserDetails")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final SystemUserCredentialsRepository systemUserCredentialsRepository;

	public UserDetailsServiceImpl(
			@Qualifier("icgSystemUserCredentialsRepository") SystemUserCredentialsRepository systemUserCredentialsRepository) {
		this.systemUserCredentialsRepository = systemUserCredentialsRepository;
	}

	@Override
	@Transactional(value = "icgTransactionManager", readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SystemUserCredential sysUserCred = systemUserCredentialsRepository.findByEmail(username).orElse(null);
		UserCredentials userCred;
		if (sysUserCred == null) {
			throw new UsernameNotFoundException("No such emailId Present");
		} else {
			/// GrantedAuthority grantedAuthority = new
			/// SimpleGrantedAuthority("ROLE_ICG_SEAT_ALLOCATER");
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysUserCred.getUserRole().getRoleName());
			Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(grantedAuthority);
			userCred = new UserCredentials(username, sysUserCred.getPassword(), true, true, true, true,
					grantedAuthorities, null, sysUserCred.getSystemUserCredId());
		}
		return userCred;
	}

}