package com.util;
import javax.servlet.http.HttpServletRequest;

public final class  AuthUtil {
	public static boolean IsLogin(HttpServletRequest r) {
		if (r==null || r.getSession() == null) {
			return false;
		}
		String name = (String) r.getSession().getAttribute("name");
		if (name == null) {
			return false;
		}
		return true;		
	}
}
