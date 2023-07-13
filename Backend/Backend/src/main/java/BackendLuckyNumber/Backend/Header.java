package BackendLuckyNumber.Backend;

public class Header {
	private String statusCode;
	private String message;
	private Boolean statusProcess;
	
	

	
	
	

	public Boolean getStatusProcess() {
		return statusProcess;
	}
	public void setStatusProcess(Boolean statusProcess) {
		this.statusProcess = statusProcess;
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