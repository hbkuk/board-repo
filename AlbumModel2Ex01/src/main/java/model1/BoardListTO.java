package model1;

import java.util.ArrayList;

public class BoardListTO {
	
	private int cpage;
	private int recordPerPage;
	private int totalRecord;
	private int totalPage;
	private int blockPerPage;
	private int startBlock;
	private int endBlock;
	
	public BoardListTO() {
		
		cpage = 1;
		recordPerPage = 10;
		blockPerPage = 5;
		
	}
	
	
	private ArrayList<BoardTO> boardArrayList;
	

	public ArrayList<BoardTO> getBoardArrayList() {
		return boardArrayList;
	}

	public void setBoardArrayList(ArrayList<BoardTO> boarArrayList) {
		this.boardArrayList = boarArrayList;
	}

	public int getCpage() {
		return cpage;
	}

	public void setCpage(int cpage) {
		this.cpage = cpage;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockPerPage() {
		return blockPerPage;
	}

	public void setBlockPerPage(int blockPerPage) {
		this.blockPerPage = blockPerPage;
	}

	public int getStartBlock() {
		return startBlock;
	}

	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}

	public int getEndBlock() {
		return endBlock;
	}

	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}

	
	

}
