package gov.cdac.services;

import java.util.List;

import javax.validation.Valid;

import gov.cdac.icgPojo.SystemUserCredential;

public interface LoginService {

	List<SystemUserCredential> findAllSysUsers();

	Boolean saveSysUserCred(@Valid SystemUserCredential sysUserObj) throws Exception;

}