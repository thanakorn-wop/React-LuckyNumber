package BackendLuckyNumber.Backend;

public class Header {
	private String statusCode;
	private String message;
	private Object datalist;
	
	
	
	public Object getDatalist() {
		return datalist;
	}
	public void setDatalist(Object datalist) {
		this.datalist = datalist;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}