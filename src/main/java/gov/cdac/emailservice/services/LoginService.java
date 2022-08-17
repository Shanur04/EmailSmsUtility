package gov.cdac.emailservice.services;

import java.util.List;

import javax.validation.Valid;

import gov.cdac.emailservice.icg.pojo.SystemUserCredential;

public interface LoginService {

	List<SystemUserCredential> findAllSysUsers();

	Boolean saveSysUserCred(@Valid SystemUserCredential sysUserObj) throws Exception;

}