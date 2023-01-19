package BackendLuckyNumber.Backend.ResponseModel;

import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.Modal.MonthModal;

public class MonthReponse {

	private Header header;
	private MonthModal dataList;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public MonthModal getDataList() {
		return dataList;
	}
	public void setDataList(MonthModal dataList) {
		this.dataList = dataList;
	}
	
	
	
	
	
}
