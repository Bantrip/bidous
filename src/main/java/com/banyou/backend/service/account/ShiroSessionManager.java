package com.banyou.backend.service.account;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

public class ShiroSessionManager extends DefaultWebSessionManager {
	private static final String COOKIE_NAME="ut";
	public ShiroSessionManager() {
		super();
		super.getSessionIdCookie().setName(COOKIE_NAME);
	}

	public void setPath(String path) {
		if (StringUtils.isNotEmpty(path))
			super.getSessionIdCookie().setPath(path);
	}

	public void setDomain(String domain) {
		if (StringUtils.isNotEmpty(domain))
			super.getSessionIdCookie().setDomain(domain);
	}
}
