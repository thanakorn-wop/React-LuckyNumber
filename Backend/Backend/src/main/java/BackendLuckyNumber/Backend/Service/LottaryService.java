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
import BackendLuckyNumber.Backend.Modal.MonthModal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.List_numberRepo;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;
import BackendLuckyNumber.Backend.Repo.MonthRepo;
import BackendLuckyNumber.Backend.Repo.TransferLottaryRepo;
import BackendLuckyNumber.Backend.RequestModel.DataSetModal;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.RequestModel.NumberRequestModel;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.RequestModel.listNumberRquestModal;
//import BackendLuckyNumber.Backend.ResponseModel.listNumberInerJoinLottaryResponseModal;
import BackendLuckyNumber.Backend.ResponseModel.ResponseData;
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
	@Autowired
	MonthRepo monthRepo;

	public List<List_number_Modal> getLottaryService(String idUser, String date) {
//		List<List_numberRepo> dataItem = new ArrayList<>();
		List<ResponseData> newData = new ArrayList<>();
		ResponseData obj = new ResponseData();
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

	public Boolean postInsertNumberService(DataSetModal NumRequest, UserdetailsIml user) throws Exception {
		Boolean status_Update = false;
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
		// List_number_Modal list_number_modal = new List_number_Modal();
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
			for (NumberRequestModel data : NumRequest.getDataSet()) {
				people_win = "";
				people_lost = "";
				total_purchase = 0;
				balance = 0;
				payNow = 0;
				notPay = 0;
				money_win = 0;
				count_lost = 0;
				count_win = 0;
				List_number_Modal list_number_modal = new List_number_Modal();
				String Cover_Number = data.getNumber().trim().replaceAll(regex, ",");
				String[] Str_Number = Cover_Number.split(",");
				Integer Length_Number = Str_Number.length;
				all_price = Length_Number * Integer.valueOf(data.getPrice());
				String joinNumber = String.join(",", Str_Number);
				if (null != joinNumber && data.getOption().equals(ConstantData.MESSAGE_TOP)
						|| data.getOption().equals(ConstantData.MESSAGE_BELOW)) {
					list_number_modal.setNumber(joinNumber);
					list_number_modal.setPrice(data.getPrice());
					list_number_modal.setAllPrice(String.valueOf(all_price));
					list_number_modal.setOptinpurchase(data.getOption());
					list_number_modal.setDatebuy(data.getDate());
					list_number_modal.setTime(formattedDate);
					list_number_modal.setStatuspayment(ConstantData.MESSAGE_NO);
					list_number_modal.setId(user.getInfoUser().getId());
					list_number_modal.setStatus("unLucky");
					list_number_modal.setLuckytime(data.getLuckytime());
					list_number_modal.setTransfer(ConstantData.MESSAGE_N);
					list_number_modal.setStatusInsert(ConstantData.MESSAGE_NOT_SUCCESS);
					list_number_modal.setSequence(data.getSequence());
					;

					listNumberRepo.save(list_number_modal);
					status_Update = true;

				} else if (null != joinNumber && data.getOption().equals(ConstantData.MESSAGE_TOD)) {
					list_number_modal.setNumber(joinNumber);
					list_number_modal.setPrice(data.getPrice());
					list_number_modal.setAllPrice(String.valueOf(all_price));
					list_number_modal.setOptinpurchase(data.getOption());
					list_number_modal.setDatebuy(data.getDate());
					list_number_modal.setTime(formattedDate);
					list_number_modal.setStatuspayment(ConstantData.MESSAGE_NO);
					list_number_modal.setId(user.getInfoUser().getId());
					list_number_modal.setStatus("unLucky");
					list_number_modal.setLuckytime(data.getLuckytime());
					list_number_modal.setTransfer(ConstantData.MESSAGE_N);
					list_number_modal.setStatusInsert(ConstantData.MESSAGE_NOT_SUCCESS);
					list_number_modal.setSequence(data.getSequence());
					listNumberRepo.save(list_number_modal);
					status_Update = true;
				}
				if (status_Update) {
					infoUser = infouserRepo.findInfoUser(id, data.getLuckytime());
					if (null != infoUser) {
						System.out.println("update ");
						if (infoUser.getStatusTransfer().equals(ConstantData.MESSAGE_N)) {
							dataItem = listNumberRepo.findItembyDate(id, data.getLuckytime());
							if (null != dataItem && dataItem.size() > 0) {
								for (List_number_Modal item : dataItem) {
									total_purchase += Integer.valueOf(item.getAllPrice());
									if (item.getTransfer().equals("N") && item.getStatus().equals("unLucky")) {
										count_lost += 1;
									}
								}
								infouserRepo.updateInfoUser(total_purchase.toString(), count_lost.toString(),
										user.getInfoUser().getNickname(), data.getLuckytime(),
										user.getInfoUser().getId());
								// listNumberRepo.updateStatusInsert(ConstantData.MESSAGE_SUCCESS,joinNumber,NumRequest.getPrice(),all_price.toString(),NumRequest.getOption(),NumRequest.getDate()
								// ,ConstantData.MESSAGE_NO,user.getInfoUser().getId(),"unLucky",NumRequest.getLuckytime(),ConstantData.MESSAGE_N);
							}
						} else {
							status_Update = false;
						}
					} else {
						dataItem = listNumberRepo.findItembyDate(id, data.getLuckytime());
						if (null != dataItem && dataItem.size() > 0) {
							for (List_number_Modal item : dataItem) {
								total_purchase += Integer.valueOf(item.getAllPrice());
								if (item.getTransfer().equals(ConstantData.MESSAGE_N)
										&& item.getStatus().equals("unLucky")) {
									count_lost += 1;
								}
							}
							InfoUserModal saveinfoUser = new InfoUserModal();
							saveinfoUser.setTotalPurchase(total_purchase.toString());
							saveinfoUser.setNickname(user.getInfoUser().getNickname());
							saveinfoUser.setPeoplelost(count_lost.toString());
							saveinfoUser.setStatusTransfer(ConstantData.MESSAGE_N);
							saveinfoUser.setDate(data.getLuckytime());
							saveinfoUser.setId(user.getInfoUser().getId());
							infouserRepo.save(saveinfoUser);
							// listNumberRepo.updateStatusInsert(ConstantData.MESSAGE_SUCCESS,joinNumber,NumRequest.getPrice(),all_price.toString(),NumRequest.getOption(),NumRequest.getDate()
							// ,ConstantData.MESSAGE_NO,user.getInfoUser().getId(),"unLucky",NumRequest.getLuckytime(),ConstantData.MESSAGE_N);
						}
					}
				}
			}

//

			System.out.println("check count_win = " + count_win);
			System.out.println("check total_purchase = " + total_purchase);

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
	public LottaryModal getLuckyItemService()
	{
		LottaryModal item = new LottaryModal();
		
		try {
			
			item = lottaryRepo.findLastDate();
		}catch(Exception e)
		{
			throw e;
		}
		return  item;
	}
	public Boolean postLuckyItemService(String luckydate,String id)
	{
		List<List_number_Modal> dataItem = null;
		Boolean status = false;
		LottaryModal luckyDate = new LottaryModal();
		ArrayList<String> idlottary = new ArrayList<>();
		
		try {
			luckyDate = lottaryRepo.findDate(luckydate);
			if(null !=luckyDate)
			{
				dataItem = listNumberRepo.findItembyDate(id,luckydate);
				if(null != dataItem && dataItem.size()> 0 )
				{
					for(List_number_Modal item : dataItem)
					{
						
						String number ="";
						number = item.getNumber();
						String[] split_number = number.split(",");
						for(String no : split_number)
						{
							if(item.getOptinpurchase().equals(ConstantData.MESSAGE_TOP))
							{
								if(no.equals(luckyDate.getThreeTop()))
								{
									
//									listNumberRepo.changeStatusLucky(id,idLottary,date);
									idlottary.add(item.getIdlist());
									
								}
							}
							else if(item.getOptinpurchase().equals(ConstantData.MESSAGE_BELOW))
							{
								
								if(no.equals(luckyDate.getTwodown()))
								{
									
									idlottary.add(item.getIdlist());					
								}
							}				
						}
					}
					if(null != idlottary && idlottary.size()>0)
					{
						listNumberRepo.changeStatusLucky(id,idlottary,luckydate);
						status = true;
					}
				}
			}
//			dataItem = listNumberRepo.findItembyDate(id,date);
//			if(null != dataItem)
//			{
//				listNumberRepo.changeStatusLucky(id,date);
//				status = true;
//			}
			
		}catch(Exception e)
		{
			throw e;
		}
//		if(null != date)
//		{
//			listNumberRepo.changeStatusLucky(id,date);
//		}
		
		return status;
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
		Integer sum = 0;
		String date ="";
		Integer oldMonth = 0;
		Integer allSum = 0;
		MonthModal monthModal = new MonthModal();
		MonthModal monthMoal2 = new MonthModal();
		LottaryModal luckytime = new LottaryModal();
		List<TransferLottaryModal> dataTransfer = new ArrayList<>();
		TransferLottaryModal itemTransfer = new TransferLottaryModal();
		List<List_number_Modal> listItem = new ArrayList<>();
		InfoUserModal infoUser = new InfoUserModal();
		MixTransferListNumberModal mixTransferNumber = new MixTransferListNumberModal();
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
		try {
			dataTransfer = listNumberRepo.getDataTransferLottaryRepo(iduser, nickname, listRequest.getLuckytime());
			if (null != dataTransfer && dataTransfer.size() > 1) {
				duplicateTransfer = true;
			}
			else {
				luckytime = lottaryRepo.findByDate( listRequest.getLuckytime());
				if(null != luckytime)
				{
					
					listItem = listNumberRepo.getItemListNumber(id, listRequest.getLuckytime());
					if (listItem.size() > 0 && listItem != null) {
						listNumberRepo.postSendLottaryRepo(id, listRequest.getLuckytime());
						infoUser = infouserRepo.findInfoUser(id, listRequest.getLuckytime());
						Integer convert_Total_Purchase = Integer.valueOf(infoUser.getTotalPurchase());
						Integer convert_Total_lost = Integer.valueOf(infoUser.getTotalLost());
						sum = convert_Total_Purchase-convert_Total_lost;	
						date = infoUser.getDate();
						String[] split_date = date.split("-");
						String year = split_date[0];
						String month = split_date[1];
						monthModal = monthRepo.findMonthOfUser(id, year);
						if(null != monthModal)
							{
								if(month.equals("01"))
								{
									if(null != monthModal.getJan() || monthModal.getJan().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getJan());
										allSum = oldMonth + sum;
										monthRepo.updateMonth1(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth1(String.valueOf(sum), id, year);
									}
								
								}
								else if(month.equals("02")){
									if(null != monthModal.getFeb() || monthModal.getFeb().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getFeb());
										allSum = oldMonth + sum;
										monthRepo.updateMonth2(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth2(String.valueOf(sum), id, year);
									}
								}
								else if(month.equals("03")){
									if(null != monthModal.getMar() || monthModal.getMar().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getMar());
										allSum = oldMonth + sum;
										monthRepo.updateMonth3(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth3(String.valueOf(sum), id, year);
									}
								}
								else if(month.equals("04")){
									if(null != monthModal.getApr() || monthModal.getApr().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getApr());
										allSum = oldMonth + sum;
										monthRepo.updateMonth4(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth4(String.valueOf(sum), id, year);
									}
				
								}
								else if(month.equals("05")){
									if(null != monthModal.getMay() || monthModal.getMay().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getMay());
										allSum = oldMonth + sum;
										monthRepo.updateMonth5(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth5(String.valueOf(sum), id, year);
									}
								}
								else if(month.equals("06")){
									if(null != monthModal.getJun() || monthModal.getJun().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getJun());
										allSum = oldMonth + sum;
										monthRepo.updateMonth6(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth6(String.valueOf(sum), id, year);
									}
								}
								else if(month.equals("07")){
									if(null != monthModal.getJul() || monthModal.getJul().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getJul());
										allSum = oldMonth + sum;
										monthRepo.updateMonth7(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth7(String.valueOf(sum), id, year);
									}
								}
								else if(month.equals("08")){
									if(null != monthModal.getAug() || monthModal.getAug().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getAug());
										allSum = oldMonth + sum;
										monthRepo.updateMonth8(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth8(String.valueOf(sum), id, year);
									}
								}
								else if(month.equals("09")){
									if(null != monthModal.getSep() || monthModal.getSep().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getSep());
										allSum = oldMonth + sum;
										monthRepo.updateMonth9(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth9(String.valueOf(sum), id, year);
									}
								}
								else if(month.equals("10")){
									if(null != monthModal.getOct() || monthModal.getOct().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getOct());
										allSum = oldMonth + sum;
										monthRepo.updateMonth10(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth10(String.valueOf(sum), id, year);
									}
								}
								else if(month.equals("11")){
									if(null != monthModal.getNov() || monthModal.getNov().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getNov());
										allSum = oldMonth + sum;
										monthRepo.updateMonth11(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth11(String.valueOf(sum), id, year);
									}
								}
								else {
									if(null != monthModal.getDec() || monthModal.getDec().equals('0'))
									{
										oldMonth = Integer.valueOf(monthModal.getDec());
										allSum = oldMonth + sum;
										monthRepo.updateMonth12(String.valueOf(allSum), id, year);
										
									}
									else {
										monthRepo.updateMonth12(String.valueOf(sum), id, year);
									}
								}
								transferStatus = true;
							}else
							{
								if(month.equals("01"))
								{
									monthMoal2.setJan(String.valueOf(sum));
								}
								else if(month.equals("02")){
									monthMoal2.setFeb(String.valueOf(sum));
								}
								else if(month.equals("03")){
									monthMoal2.setMar(String.valueOf(sum));
								}
								else if(month.equals("04")){
									monthMoal2.setApr(String.valueOf(sum));
				
								}
								else if(month.equals("05")){
									monthMoal2.setMay(String.valueOf(sum));
								}
								else if(month.equals("06")){
									monthMoal2.setJun(String.valueOf(sum));
								}
								else if(month.equals("07")){
									monthMoal2.setJul(String.valueOf(sum));
								}
								else if(month.equals("08")){
									monthMoal2.setAug(String.valueOf(sum));
								}
								else if(month.equals("09")){
									monthMoal2.setSep(String.valueOf(sum));
								}
								else if(month.equals("10")){
									monthMoal2.setOct(String.valueOf(sum));
								}
								else if(month.equals("11")){
									monthMoal2.setNov(String.valueOf(sum));
								}
								else {
									monthMoal2.setDec(String.valueOf(sum));
								}
								monthRepo.save(monthMoal2);
								transferStatus = true;
							}
						if (transferStatus) {
							itemTransfer.setIduser(iduser);
							itemTransfer.setNickname(nickname);
							itemTransfer.setDate(listRequest.getLuckytime());
							itemTransfer.setTimeTransfer(formattedDate);
							transferLottaryRepo.save(itemTransfer);
							
						}
					}
				}

			}
//			listItem = listNumberRepo.getItemListNumber(id, listRequest.getLuckytime());
//			if (listItem.size() > 0 && listItem != null) {
//				System.out.println("check");
//				dataTransfer = listNumberRepo.getDataTransferLottaryRepo(iduser, nickname, listRequest.getLuckytime());
//				if (null != dataTransfer && dataTransfer.size() > 1) {
//					duplicateTransfer = true;
//				} else {
//					listNumberRepo.postSendLottaryRepo(id, listRequest.getLuckytime());
//					infoUser = infouserRepo.findInfoUser(id, listRequest.getLuckytime());
//					Integer convert_Total_Purchase = Integer.valueOf(infoUser.getTotalPurchase());
//					Integer convert_Total_lost = Integer.valueOf(infoUser.getTotalLost());
//					sum = convert_Total_Purchase-convert_Total_lost;	
//					date = infoUser.getDate();
//					String[] split_date = date.split("-");
//					String year = split_date[0];
//					String month = split_date[1];
//					monthModal = monthRepo.findMonthOfUser(id, year);
//					if(null != monthModal)
//					{
//						if(month.equals("01"))
//						{
//							if(null != monthModal.getJan() || monthModal.getJan().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getJan());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth1(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth1(String.valueOf(sum), id, year);
//							}
//						
//						}
//						else if(month.equals("02")){
//							if(null != monthModal.getFeb() || monthModal.getFeb().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getFeb());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth2(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth2(String.valueOf(sum), id, year);
//							}
//						}
//						else if(month.equals("03")){
//							if(null != monthModal.getMar() || monthModal.getMar().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getMar());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth3(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth3(String.valueOf(sum), id, year);
//							}
//						}
//						else if(month.equals("04")){
//							if(null != monthModal.getApr() || monthModal.getApr().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getApr());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth4(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth4(String.valueOf(sum), id, year);
//							}
//		
//						}
//						else if(month.equals("05")){
//							if(null != monthModal.getMay() || monthModal.getMay().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getMay());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth5(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth5(String.valueOf(sum), id, year);
//							}
//						}
//						else if(month.equals("06")){
//							if(null != monthModal.getJun() || monthModal.getJun().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getJun());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth6(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth6(String.valueOf(sum), id, year);
//							}
//						}
//						else if(month.equals("07")){
//							if(null != monthModal.getJul() || monthModal.getJul().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getJul());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth7(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth7(String.valueOf(sum), id, year);
//							}
//						}
//						else if(month.equals("08")){
//							if(null != monthModal.getAug() || monthModal.getAug().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getAug());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth8(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth8(String.valueOf(sum), id, year);
//							}
//						}
//						else if(month.equals("09")){
//							if(null != monthModal.getSep() || monthModal.getSep().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getSep());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth9(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth9(String.valueOf(sum), id, year);
//							}
//						}
//						else if(month.equals("10")){
//							if(null != monthModal.getOct() || monthModal.getOct().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getOct());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth10(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth10(String.valueOf(sum), id, year);
//							}
//						}
//						else if(month.equals("11")){
//							if(null != monthModal.getNov() || monthModal.getNov().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getNov());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth11(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth11(String.valueOf(sum), id, year);
//							}
//						}
//						else {
//							if(null != monthModal.getDec() || monthModal.getDec().equals('0'))
//							{
//								oldMonth = Integer.valueOf(monthModal.getDec());
//								allSum = oldMonth + sum;
//								monthRepo.updateMonth12(String.valueOf(allSum), id, year);
//								
//							}
//							else {
//								monthRepo.updateMonth12(String.valueOf(sum), id, year);
//							}
//						}
//					}else
//					{
//						if(month.equals("01"))
//						{
//							monthMoal2.setJan(String.valueOf(sum));
//						}
//						else if(month.equals("02")){
//							monthMoal2.setFeb(String.valueOf(sum));
//						}
//						else if(month.equals("03")){
//							monthMoal2.setMar(String.valueOf(sum));
//						}
//						else if(month.equals("04")){
//							monthMoal2.setApr(String.valueOf(sum));
//		
//						}
//						else if(month.equals("05")){
//							monthMoal2.setMay(String.valueOf(sum));
//						}
//						else if(month.equals("06")){
//							monthMoal2.setJun(String.valueOf(sum));
//						}
//						else if(month.equals("07")){
//							monthMoal2.setJul(String.valueOf(sum));
//						}
//						else if(month.equals("08")){
//							monthMoal2.setAug(String.valueOf(sum));
//						}
//						else if(month.equals("09")){
//							monthMoal2.setSep(String.valueOf(sum));
//						}
//						else if(month.equals("10")){
//							monthMoal2.setOct(String.valueOf(sum));
//						}
//						else if(month.equals("11")){
//							monthMoal2.setNov(String.valueOf(sum));
//						}
//						else {
//							monthMoal2.setDec(String.valueOf(sum));
//						}
//						monthRepo.save(monthMoal2);
//					}
//					transferStatus = true;
//					if (transferStatus) {
//						itemTransfer.setIduser(iduser);
//						itemTransfer.setNickname(nickname);
//						itemTransfer.setDate(listRequest.getLuckytime());
//						itemTransfer.setTimeTransfer(formattedDate);
//						transferLottaryRepo.save(itemTransfer);
//						
//					}
//				}
//			}
			mixTransferNumber.setDuplicateTransfer(duplicateTransfer);
			mixTransferNumber.setListNumberModal(listItem);
			mixTransferNumber.setLuckytime(luckytime);

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
