package com.moving.utils;

import javax.servlet.http.HttpSession;

public class AuthUtils {

	public static boolean isAuth(HttpSession session){
		if(session.getAttribute("authenticated") != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void setAuth(HttpSession session, boolean isAuth, String userId) {
		session.setAttribute("authenticated", isAuth? Boolean.TRUE : null);
		session.setAttribute("userId", userId == null? null : userId);
	}
	
	public static String userId(HttpSession session){
		return session.getAttribute("userId").toString();
	}
}
