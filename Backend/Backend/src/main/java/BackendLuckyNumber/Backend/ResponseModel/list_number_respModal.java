package BackendLuckyNumber.Backend.ResponseModel;

import javax.persistence.Column;

import BackendLuckyNumber.Backend.Header;

public class list_number_respModal {

	
	private Header header;
	private Object Datalist;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Object getDatalist() {
		return Datalist;
	}
	public void setDatalist(Object datalist) {
		Datalist = datalist;
	}
	
	
	
	
}
