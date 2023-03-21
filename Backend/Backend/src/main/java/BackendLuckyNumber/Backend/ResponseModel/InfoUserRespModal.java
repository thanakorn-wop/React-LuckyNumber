package BackendLuckyNumber.Backend.ResponseModel;

import java.util.List;

import javax.persistence.Column;

import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;

public class InfoUserRespModal {
	private Header header;
	private List<InfoUserModal> Datalist;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public List<InfoUserModal> getDatalist() {
		return Datalist;
	}
	public void setDatalist(List<InfoUserModal> datalist) {
		Datalist = datalist;
	}
	
	
	
	
	
	
	
	

	
	
	

}
