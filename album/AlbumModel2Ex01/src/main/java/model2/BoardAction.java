package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardAction {
	
	public abstract void excute( HttpServletRequest request, HttpServletResponse response);
}
