package com.banyou.backend.web;

import org.apache.shiro.SecurityUtils;

import com.banyou.backend.entity.Merchant;
import com.banyou.backend.service.account.ShiroDbRealm.ShiroUser;

public class UserContext {
	private static ShiroUser EMPTY_USER=new ShiroUser(null, null, null, Merchant.NULL.getId(), Merchant.NULL.getName(), Merchant.NULL.getType());;
	public static ShiroUser getUser(){
		Object principal = SecurityUtils.getSubject().getPrincipal();
		if (principal == null || !(principal instanceof ShiroUser)) {
			return EMPTY_USER;
		} else {
			return ((ShiroUser) principal);
		}
	}
	public static  Long getUserId() {
		ShiroUser user=getUser();
		return user.id;

	}
	public static boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}
	public static boolean isSuper() {
		return hasRole("admin");
	}
}
