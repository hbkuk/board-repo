package model1;

import java.util.ArrayList;

public class BoardTO {
	
	private String seq;
	
	private String filename;
	private long filesize;
	
	private String subject;
	
	private String cmt;
	
	private String writer;
	
	private String wdate;
	
	private String hit;
	
	private int wgap;
	
	private String password;
	
	private String content;
	
	private String latitude;
	
	private String longitude;
	
	private String mail;
	
	private String wip;
	
	private ArrayList<CommentTO> cmtLists;
	
	

	public ArrayList<CommentTO> getCmtLists() {
		return cmtLists;
	}

	public void setCmtLists(ArrayList<CommentTO> cmtLists) {
		this.cmtLists = cmtLists;
	}

	public String getWip() {
		return wip;
	}

	public void setWip(String wip) {
		this.wip = wip;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCmt() {
		return cmt;
	}

	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public int getWgap() {
		return wgap;
	}

	public void setWgap(int wgap) {
		this.wgap = wgap;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
	
	

}
