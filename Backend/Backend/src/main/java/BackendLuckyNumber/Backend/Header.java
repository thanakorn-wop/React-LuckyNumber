package BackendLuckyNumber.Backend;

public class Header {
	private String statusCode;
	private String message;
	private String statusMessage;
	private Boolean statusProcess;
	
	
	

	
	
	

	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
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