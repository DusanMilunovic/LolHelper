package com.lolpicker.model;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("10m")
public class RequestIp {
	private String ipAddress;
	private boolean suspicious = false;
	public RequestIp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestIp(String ipAddress) {
		super();
		this.ipAddress = ipAddress;
		this.suspicious = false;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public boolean isSuspicious() {
		return suspicious;
	}
	public void setSuspicious(boolean suspicious) {
		this.suspicious = suspicious;
	}
	
}
