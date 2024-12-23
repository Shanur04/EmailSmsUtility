package gov.cdac.services;
/**
 * 
 * @author shanurj
 *
 */

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gov.cdac.icgPojo.SystemUserCredential;
import gov.cdac.icgPojo.UserRole;
import gov.cdac.icgRepositories.SystemUserCredentialsRepository;
import jakarta.validation.Valid;


@Service("icgLoginService")
public class LoginServiceImpl implements LoginService  {
	
	@Autowired
	SystemUserCredentialsRepository systemUserCredentialsRepository;

	@Override
	public List<SystemUserCredential> findAllSysUsers() {
		return systemUserCredentialsRepository.findAll();
	}

	@Override
	public Boolean saveSysUserCred(@Valid SystemUserCredential sysUserObj) throws Exception{
		try {
			UserRole userRole = new UserRole();
			userRole.setRoleId(11);
			sysUserObj.setUserRole(userRole);// Hard Coded the user role of seat allocator
			sysUserObj.setStatus(true);
			sysUserObj.setPasswordResetStatus(false);
			sysUserObj.setRecordTracking(new Timestamp(System.currentTimeMillis()));
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
			String encodedPassword = encoder.encode(sysUserObj.getPassword());
			sysUserObj.setPassword(encodedPassword);
			systemUserCredentialsRepository.save(sysUserObj);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	

}