package BackendLuckyNumber.Backend.Modal;

import java.util.List;

public class MixTransferListNumberModal {

	private List<List_number_Modal> listNumberModal;
	private List<TransferLottaryModal> transferLottaryModal;
	private LottaryModal luckytime;
	private Boolean duplicateTransfer;
	
	

	
	public LottaryModal getLuckytime() {
		return luckytime;
	}
	public void setLuckytime(LottaryModal luckytime) {
		this.luckytime = luckytime;
	}
	public Boolean getDuplicateTransfer() {
		return duplicateTransfer;
	}
	public void setDuplicateTransfer(Boolean duplicateTransfer) {
		this.duplicateTransfer = duplicateTransfer;
	}
	public List<List_number_Modal> getListNumberModal() {
		return listNumberModal;
	}
	public void setListNumberModal(List<List_number_Modal> listNumberModal) {
		this.listNumberModal = listNumberModal;
	}
	public List<TransferLottaryModal> getTransferLottaryModal() {
		return transferLottaryModal;
	}
	public void setTransferLottaryModal(List<TransferLottaryModal> transferLottaryModal) {
		this.transferLottaryModal = transferLottaryModal;
	}

	
	
	
	
}
