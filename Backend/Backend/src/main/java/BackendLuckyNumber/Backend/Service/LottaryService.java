package BackendLuckyNumber.Backend.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.List_number_Modal;
import BackendLuckyNumber.Backend.Modal.LottaryModal;
import BackendLuckyNumber.Backend.Modal.MixTransferListNumberModal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.List_numberRepo;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;
import BackendLuckyNumber.Backend.Repo.TransferLottaryRepo;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.RequestModel.NumberRequestModel;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.RequestModel.listNumberRquestModal;
//import BackendLuckyNumber.Backend.ResponseModel.listNumberInerJoinLottaryResponseModal;
import BackendLuckyNumber.Backend.ResponseModel.list_number_respModal;
import BackendLuckyNumber.Backend.Constant.ConstantData;
import org.apache.commons.lang3.StringUtils;

@Service
public class LottaryService {

	@Autowired
	LottaryRepo lottaryRepo;

	@Autowired
	List_numberRepo listNumberRepo;

	@Autowired
	TransferLottaryRepo transferLottaryRepo;
	
	@Autowired
	InfoUserRepo infouserRepo;
	

	public List<List_number_Modal> getLottaryService(String idUser, String date) {
//		List<List_numberRepo> dataItem = new ArrayList<>();
		List<list_number_respModal> newData = new ArrayList<>();
		list_number_respModal obj = new list_number_respModal();
		List<List_number_Modal> dataItem = null;
		LottaryModal lottaryDate = new LottaryModal();
		// List<listNumberInerJoinLottaryResponseModal> item = new ArrayList<>();

		try {
			if (date.equals("lastdata")) {
//					LottaryModal dataLottary = lottaryRepo.findDate();
				dataItem = listNumberRepo.findItem(idUser);
			} else {
				LottaryModal dataLottary = lottaryRepo.findSelectDate(date);
				if (null != dataLottary && dataLottary.getDate() != null) {
//					dataItem = listNumberRepo.findItem(idUser, dataLottary.getDate());
				} else {
					lottaryDate.setDate(date);
					lottaryRepo.save(lottaryDate);
//						dataItem =  listNumberRepo.findJoinItem(idUser,date);		
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return dataItem;
	}

	public Boolean postInsertNumberLuckyService(LuckyNumberReq luckyNumberReq) throws Exception {
		Boolean status = false;
		LottaryModal lottary = new LottaryModal();

		lottary.setDate(luckyNumberReq.getDate());
		lottary.setThreedow(luckyNumberReq.getThreedown());
		lottary.setThreeTop(luckyNumberReq.getThreetop());
		lottary.setTwodown(luckyNumberReq.getTwodown());
		lottary.setTwotop(luckyNumberReq.getTwotop());
		try {
			LottaryModal dataLottary = lottaryRepo.findByDate(luckyNumberReq.getDate());
			if (null == dataLottary) {

				lottaryRepo.save(lottary);

				status = true;
			} else {
				System.out.println("Duplicate date ");
				status = false;
			}

		} catch (Exception e) {
			throw new Exception("ERROR  Lottary is = " + e);

		}
		return status;

	}

	public Boolean postUpdateLuckyNumberService(LuckyNumberReq luckyNumberReq) {
		Boolean statusUpdate = false;
		try {

			List<LottaryModal> LastDate = lottaryRepo.findLastDate(luckyNumberReq.getDate());
			// Optional<List_number_Modal> allDataUser =
			// listNumberRepo.findById(user.getInfoUser().getId());
			if (null != LastDate && LastDate.size() > 1) {
				lottaryRepo.updateStatusLucky(LastDate.get(0).getDate(), LastDate.get(1).getDate(),
						luckyNumberReq.getThreetop(), luckyNumberReq.getTwodown(), luckyNumberReq.getTwotop());
				statusUpdate = true;
				System.out.println("check statusUpdate Many " + statusUpdate);

			} else {
				lottaryRepo.updateStatusLuckyOneonOne(LastDate.get(0).getDate(), luckyNumberReq.getThreetop(),
						luckyNumberReq.getTwodown(), luckyNumberReq.getTwotop());
				statusUpdate = true;
				System.out.println("check statusUpdate one on one  " + statusUpdate);
			}

		} catch (Exception e) {
			System.out.println("Not update = " + statusUpdate);
			throw e;

		}
		return statusUpdate;
	}

	public Boolean postInsertNumberService(NumberRequestModel NumRequest, UserdetailsIml user) throws Exception {
		Boolean status_Update = false;
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
		List_number_Modal list_number_modal = new List_number_Modal();
		InfoUserModal infoUser = new InfoUserModal();
		List<List_number_Modal> dataItem = null;
		String formattedDate = myDateObj.format(myFormatObj);
		String regex = "[, ' ']";
		String id = user.getInfoUser().getId();
		String people_win = "";
		String people_lost = "";
		Integer total_purchase = 0;
		Integer balance = 0;
		Integer payNow = 0;
		Integer notPay = 0;
		Integer money_win = 0;
		Integer count_lost = 0;
		Integer count_win = 0;

//		Formatter formatter = new Formatter();
//		formatter = new Formatter();
//		formatter.format("%.8s", timenow);

		Integer all_price = 0;
		try {
			String Cover_Number = NumRequest.getNumber().trim().replaceAll(regex, ",");
			String[] Str_Number = Cover_Number.split(",");
			Integer Length_Number = Str_Number.length;
			all_price = Length_Number * Integer.valueOf(NumRequest.getPrice());
			String joinNumber = String.join(",", Str_Number);

			if (null != joinNumber && NumRequest.getOption().equals(ConstantData.MESSAGE_TOP)
					|| NumRequest.getOption().equals(ConstantData.MESSAGE_BELOW)) {
				list_number_modal.setNumber(joinNumber);
				list_number_modal.setPrice(NumRequest.getPrice());
				list_number_modal.setAll_price(String.valueOf(all_price));
				list_number_modal.setOptinpurchase(NumRequest.getOption());
				list_number_modal.setDatebuy(NumRequest.getDate());
				list_number_modal.setTime(formattedDate);
				list_number_modal.setStatuspayment(ConstantData.MESSAGE_NO);
				list_number_modal.setId(user.getInfoUser().getId());
				list_number_modal.setStatus("unLucky");
				list_number_modal.setLuckytime(NumRequest.getLuckytime());
				list_number_modal.setTransfer(ConstantData.MESSAGE_N);
				list_number_modal.setStatusInsert(ConstantData.MESSAGE_NOT_SUCCESS);
				listNumberRepo.save(list_number_modal);
				status_Update = true;

			} else if (null != joinNumber && NumRequest.getOption().equals(ConstantData.MESSAGE_TOD)) {
				list_number_modal.setNumber(joinNumber);
				list_number_modal.setPrice(NumRequest.getPrice());
				list_number_modal.setAll_price(String.valueOf(all_price));
				list_number_modal.setOptinpurchase(NumRequest.getOption());
				list_number_modal.setDatebuy(NumRequest.getDate());
				list_number_modal.setTime(formattedDate);
				list_number_modal.setStatuspayment(ConstantData.MESSAGE_NO);
				list_number_modal.setId(user.getInfoUser().getId());
				list_number_modal.setStatus("unLucky");
				list_number_modal.setLuckytime(NumRequest.getLuckytime());
				list_number_modal.setTransfer(ConstantData.MESSAGE_N);
				list_number_modal.setStatusInsert(ConstantData.MESSAGE_NOT_SUCCESS);
				listNumberRepo.save(list_number_modal);
				status_Update = true;
			}
			if (status_Update) {
				infoUser = infouserRepo.findInfoUser(id,NumRequest.getLuckytime());
				if (null != infoUser) {
					System.out.println("update ");
					if(infoUser.getStatusTransfer().equals(ConstantData.MESSAGE_N))
					{
						dataItem = listNumberRepo.findItembyDate(id,NumRequest.getLuckytime());
						if (null != dataItem && dataItem.size() > 0) {
							for (List_number_Modal item : dataItem) {
								total_purchase += Integer.valueOf(item.getAll_price());
								if (item.getTransfer().equals("N") && item.getStatus().equals("unLucky")) {
									count_lost += 1;
								}
							}
							infouserRepo.updateInfoUser(total_purchase.toString(),count_lost.toString(),user.getInfoUser().getNickname(),NumRequest.getLuckytime(),user.getInfoUser().getId());
						//	listNumberRepo.updateStatusInsert(ConstantData.MESSAGE_SUCCESS,joinNumber,NumRequest.getPrice(),all_price.toString(),NumRequest.getOption(),NumRequest.getDate()
								//	,ConstantData.MESSAGE_NO,user.getInfoUser().getId(),"unLucky",NumRequest.getLuckytime(),ConstantData.MESSAGE_N);
						}
					}
					else {
						status_Update = false;
					}
				} else {
					dataItem = listNumberRepo.findItembyDate(id,NumRequest.getLuckytime());
					if (null != dataItem && dataItem.size() > 0) {
						for (List_number_Modal item : dataItem) {
							total_purchase += Integer.valueOf(item.getAll_price());
							if (item.getTransfer().equals(ConstantData.MESSAGE_N) && item.getStatus().equals("unLucky")) {
								count_lost += 1;
							}
						}
						InfoUserModal saveinfoUser = new InfoUserModal();
						saveinfoUser.setTotalPurchase(total_purchase.toString());
						saveinfoUser.setNickname(user.getInfoUser().getNickname());
						saveinfoUser.setPeoplelost(count_lost.toString());
						saveinfoUser.setStatusTransfer(ConstantData.MESSAGE_N);
						saveinfoUser.setDate(NumRequest.getLuckytime());
						saveinfoUser.setId(user.getInfoUser().getId());
						infouserRepo.save(saveinfoUser);
					//	listNumberRepo.updateStatusInsert(ConstantData.MESSAGE_SUCCESS,joinNumber,NumRequest.getPrice(),all_price.toString(),NumRequest.getOption(),NumRequest.getDate()
								//,ConstantData.MESSAGE_NO,user.getInfoUser().getId(),"unLucky",NumRequest.getLuckytime(),ConstantData.MESSAGE_N);
					}
				}
			}
			System.out.println("check count_win = "+count_win);
			System.out.println("check total_purchase = "+total_purchase);
			
		} catch (Exception e) {
			
			throw new Exception("Error Lottary Service = " + e);
		}

		return status_Update;

	}

	public Boolean postUpdateStatusPaymentService(listNumberRquestModal listRequest, Authentication auth) {
		Boolean updateStatus = false;
		try {

			listNumberRepo.postUpdateStatusPaymentRepo(listRequest.getStatuspayment(), listRequest.getId(),
					listRequest.getIdlist());
			updateStatus = true;
		} catch (Exception e) {
			throw e;
		}
		return updateStatus;
	}

	public Boolean postDeleteDataService(listNumberRquestModal listRequest, Authentication auth) {
		Boolean DeleteStatus = false;
		try {

			listNumberRepo.postDeleteDataRepo(listRequest.getId(), listRequest.getIdlist());
			DeleteStatus = true;
		} catch (Exception e) {
			throw e;
		}
		return DeleteStatus;
	}

	public MixTransferListNumberModal postSendLottaryService(listNumberRquestModal listRequest, String nickname,
			String iduser, String id) {
		Boolean transferStatus = false;
		Boolean duplicateTransfer = false;
		List<TransferLottaryModal> dataTransfer = new ArrayList<>();
		TransferLottaryModal itemTransfer = new TransferLottaryModal();
		List<List_number_Modal> listItem = new ArrayList<>();
		MixTransferListNumberModal mixTransferNumber = new MixTransferListNumberModal();
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
		try {
			listItem = listNumberRepo.getItemListNumber(id, listRequest.getLuckytime());
			if (listItem.size() > 0 && listItem != null) {
				System.out.println("check");
				dataTransfer = listNumberRepo.getDataTransferLottaryRepo(iduser, nickname, listRequest.getLuckytime());
				if (null != dataTransfer && dataTransfer.size() > 0) {
					duplicateTransfer = true;
				} else {
					listNumberRepo.postSendLottaryRepo(id, listRequest.getLuckytime());
					transferStatus = true;
					if (transferStatus) {
						itemTransfer.setIduser(iduser);
						itemTransfer.setNickname(nickname);
						itemTransfer.setDate(listRequest.getLuckytime());
						itemTransfer.setTimeTransfer(formattedDate);
						transferLottaryRepo.save(itemTransfer);
					}
				}
			}
			mixTransferNumber.setDuplicateTransfer(duplicateTransfer);
			mixTransferNumber.setListNumberModal(listItem);

//			
//	
//			listNumberRepo.postSendLottaryRepo(listRequest.getId(),listRequest.getIdlist());
//			transferStatus = true;
		} catch (Exception e) {
			throw e;
		}
		return mixTransferNumber;
	}

}
