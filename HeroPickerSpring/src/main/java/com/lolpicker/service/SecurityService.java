package com.lolpicker.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lolpicker.model.RequestIp;

@Service
public class SecurityService {

	@Autowired
	@Qualifier("heroPickerSession")
	private KieSession securitySession;
	
	public boolean checkSpam(String ipAddress) {
		RequestIp ri = new RequestIp(ipAddress);
		securitySession.insert(ri);
		securitySession.fireAllRules();
		return ri.isSuspicious();
	}
}
