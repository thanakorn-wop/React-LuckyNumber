package BackendLuckyNumber.Backend.ResponseModel;

import java.util.List;

import javax.persistence.Column;

import BackendLuckyNumber.Backend.Header;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;

public class InfoUserRespModal {
	private Header header;
	private List<InfoUserModal> DataList;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public List<InfoUserModal> getDataList() {
		return DataList;
	}
	public void setDataList(List<InfoUserModal> dataList) {
		DataList = dataList;
	}
	
	
	
	
	
	
	

	
	
	

}
