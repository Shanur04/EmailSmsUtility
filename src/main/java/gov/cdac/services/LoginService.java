package gov.cdac.services;

import java.util.List;

import gov.cdac.icgPojo.SystemUserCredential;
import jakarta.validation.Valid;

public interface LoginService {

	List<SystemUserCredential> findAllSysUsers();

	Boolean saveSysUserCred(@Valid SystemUserCredential sysUserObj) throws Exception;

}