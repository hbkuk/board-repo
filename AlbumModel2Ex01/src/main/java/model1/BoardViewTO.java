package model1;

public class BoardViewTO {
	
	private String nSeq;
	private String nSubject;
	
	private String bSeq;
	private String bSubject;
	
	private BoardTO to;

	public String getnSeq() {
		return nSeq;
	}

	public void setnSeq(String nSeq) {
		this.nSeq = nSeq;
	}

	public String getnSubject() {
		return nSubject;
	}

	public void setnSubject(String nSubject) {
		this.nSubject = nSubject;
	}

	public String getbSeq() {
		return bSeq;
	}

	public void setbSeq(String bSeq) {
		this.bSeq = bSeq;
	}

	public String getbSubject() {
		return bSubject;
	}

	public void setbSubject(String bSubject) {
		this.bSubject = bSubject;
	}

	public BoardTO getTo() {
		return to;
	}

	public void setTo(BoardTO to) {
		this.to = to;
	}
	
	

}
