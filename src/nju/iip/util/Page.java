package nju.iip.util;

/**
 * 分页组件
 * 
 * @author Administrator
 * 
 */
public class Page {

	// 总记录数
	private int totalRecords;

	// 当前页
	private int pageNo;

	// 每页显示多少条
	private int pageSize;

	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * 取得总页数
	 * 
	 * @return
	 */
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 取得第一页
	 * 
	 * @return
	 */
	public int getTopPageNo() {
		return 0;
	}

	/**
	 * 取得上一页
	 * 
	 * @return
	 */
	public int getPreviousPageNo() {
		if (pageNo <= 1) {
			return 0;
		}
		return pageNo - 1;
	}

	/**
	 * 取得下一页
	 * 
	 * @return
	 */
	public int getNextPageNo() {
		if (pageNo >= getTotalPages()) {
			return getTotalPages() == 0 ? 1 : getTotalPages();
		}
		return pageNo + 1;
	}

	/**
	 * 取得page.begin
	 * 
	 * @return
	 */
	public int getBegin() {
		if (pageNo < 5) {
			return 0;
		}
		return pageNo - 4;
	}

	/**
	 * 取得page.end
	 * 
	 * @return
	 */
	public int getEnd() {
		if ((pageNo < 5) && (getTotalPages() > 10)) {
			return 9;
		}
		return ((pageNo + 5) < getTotalPages()) ? (pageNo + 5)
				: (getTotalPages() - 1);
	}

	public int getBottomPageNo() {
		return getTotalPages() == 0 ? 0 : getTotalPages() - 1;
	}
}