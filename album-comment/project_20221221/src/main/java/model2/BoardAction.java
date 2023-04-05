package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardAction {
	
	public static String UPLOADPATH = "C:\\java2\\spring2-workspace\\20221221\\project01\\src\\main\\webapp\\upload";
	
	public abstract void execute( HttpServletRequest request, HttpServletResponse response );

}
