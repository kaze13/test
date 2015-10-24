package com.moving.session;

public final class SessionInfo {

	private static final ThreadLocal<User> USERINFO = new ThreadLocal<>();
	
	public static String getUserId(){
		return USERINFO.get().getUserId();
	}
	
	public static User getUser() {
		return USERINFO.get();
	}
	
	public static void setUser(User userInfo){
		USERINFO.set(userInfo);
	}
	
	public static void removeUser(){
		USERINFO.remove();
	}
	
	public static boolean isAuth(){
		return SessionInfo.getUser() == null;
	}
	
}
