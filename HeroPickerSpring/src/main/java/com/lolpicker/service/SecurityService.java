package com.lolpicker.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lolpicker.model.RequestIp;

@Service
public class SecurityService {

	private final KieSession securitySession;

	@Autowired
	public SecurityService(KieSession kieSession) {
		this.securitySession = kieSession;
	}
	
	public boolean checkSpam(String ipAddress) {
		RequestIp ri = new RequestIp(ipAddress);
		securitySession.insert(ri);
		securitySession.fireAllRules();
		return ri.isSuspicious();
	}
}
