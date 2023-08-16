package BackendLuckyNumber.Backend.Service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.List_number_Modal;
import BackendLuckyNumber.Backend.Modal.LottaryModal;
import BackendLuckyNumber.Backend.Modal.MixTransferListNumberModal;
import BackendLuckyNumber.Backend.Modal.MonthModal;
import BackendLuckyNumber.Backend.Modal.PriceLottaryModal;
import BackendLuckyNumber.Backend.Modal.SuccessAndFailModal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.List_numberRepo;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;
import BackendLuckyNumber.Backend.Repo.MonthRepo;
import BackendLuckyNumber.Backend.Repo.PriceLottaryRepo;
import BackendLuckyNumber.Backend.Repo.TransferLottaryRepo;
import BackendLuckyNumber.Backend.RequestModel.DataSetModal;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.RequestModel.NumberRequestModel;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;
import BackendLuckyNumber.Backend.RequestModel.listNumberRquestModal;
//import BackendLuckyNumber.Backend.ResponseModel.listNumberInerJoinLottaryResponseModal;
import BackendLuckyNumber.Backend.ResponseModel.ResponseData;
import BackendLuckyNumber.Backend.Constant.ConstantData;

import org.apache.commons.lang3.ArrayUtils;
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

	@Autowired
	PriceLottaryRepo priceLottaryRepo;
	
	private NumberFormat numberFormatter = NumberFormat.getInstance(Locale.getDefault());

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

	public SuccessAndFailModal postInsertNumberService(DataSetModal NumRequest, UserdetailsIml user) throws Exception {
		Boolean status_Update = false;
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
		SuccessAndFailModal successAndFaiModal = new SuccessAndFailModal();
		successAndFaiModal.setStatusSuccess(false);
		// List_number_Modal list_number_modal = new List_number_Modal();
		LottaryModal luckyLottary = new LottaryModal();
		InfoUserModal infoUser = null;
		List<List_number_Modal> dataItem = null;
		String formattedDate = myDateObj.format(myFormatObj);
		String regex = "[, ' ']";
		String id = user.getInfoUser().getId();
		String people_win = "";
		String people_lost = "";
		String oldSequence = "";
		Integer total_purchase = 0;
		Integer balance = 0;
		Integer payNow = 0;
		Integer notPay = 0;
		Integer money_win = 0;
		Integer count_lost = 0;
		Integer count_win = 0;
		Integer totalBuy = 0;
		NumberFormat numberFormatter = NumberFormat.getInstance(Locale.getDefault());
//		Formatter formatter = new Formatter();
//		formatter = new Formatter();
//		formatter.format("%.8s", timenow);

		Integer all_price = 0;
		Boolean statusTransfer = false;
		try {

			for (NumberRequestModel data : NumRequest.getDataSet()) {
				luckyLottary = lottaryRepo.findByDate(data.getLuckytime());
				if (null != luckyLottary) {
					if (!luckyLottary.getStatusLottary().equals(ConstantData.MESSAGE_N)) {
						statusTransfer = true;
						break;
					}
				}
			}
			if (statusTransfer) {
				successAndFaiModal.setMessage(ConstantData.MESSAGE_LUCKYTIME_OVER);
			} else {

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
					infoUser = infouserRepo.findInfoUser(id, data.getLuckytime());
					list_number_modal.setNumber(joinNumber);
					list_number_modal.setPrice(data.getPrice());
					list_number_modal.setAllPrice(String.valueOf(all_price));
					list_number_modal.setOptinpurchase(data.getOption());
					list_number_modal.setDatebuy(data.getDate());
					list_number_modal.setTime(formattedDate);
					list_number_modal.setStatuspayment(ConstantData.MESSAGE_NO);
					list_number_modal.setId(id);
					list_number_modal.setStatus("unLucky");
					list_number_modal.setLuckytime(data.getLuckytime());
					list_number_modal.setTransfer(ConstantData.MESSAGE_N);
					list_number_modal.setStatusValidate(ConstantData.MESSAGE_N);
					list_number_modal.setSequence(data.getSequence());
					list_number_modal.setReward("0");
					listNumberRepo.save(list_number_modal);
					successAndFaiModal.setStatusSuccess(true);
					successAndFaiModal.setMessage(ConstantData.MESSAGE_SUCCESS);
					status_Update = true;
					if (null != infoUser) {
						System.out.println("update ");
						dataItem = listNumberRepo.findItembyDate(id, data.getLuckytime());
						String[] arrSequence = new String[dataItem.size()];
						Set<String> set = new LinkedHashSet<>();
						if (null != dataItem && dataItem.size() > 0) {
							Integer index = 0;
							for (List_number_Modal item : dataItem) {
								total_purchase += Integer.valueOf(item.getAllPrice());
								arrSequence[index] = item.getSequence();
								set.add(item.getSequence());
								index += 1;
							}
							String peopleLost = String.valueOf(set.size());
							String totalPurchase = numberFormatter.format(total_purchase);
							// Object[] result = Arrays.stream(arrSequence).distinct().toArray();
							infouserRepo.updateInfoUser(totalPurchase, peopleLost, user.getInfoUser().getNickname(),
									data.getLuckytime(), user.getInfoUser().getId());
							successAndFaiModal.setStatusSuccess(true);
							successAndFaiModal.setMessage(ConstantData.MESSAGE_SUCCESS);
							// listNumberRepo.updateStatusInsert(ConstantData.MESSAGE_SUCCESS,joinNumber,NumRequest.getPrice(),all_price.toString(),NumRequest.getOption(),NumRequest.getDate()
							// ,ConstantData.MESSAGE_NO,user.getInfoUser().getId(),"unLucky",NumRequest.getLuckytime(),ConstantData.MESSAGE_N);
						}

					} else {

						InfoUserModal saveinfoUser = new InfoUserModal();
						saveinfoUser.setTotalPurchase(data.getAllPrice());
						saveinfoUser.setNickname(user.getInfoUser().getNickname());
						saveinfoUser.setPeoplelost("1");
						saveinfoUser.setStatusTransfer(ConstantData.MESSAGE_N);
						saveinfoUser.setDate(data.getLuckytime());
						saveinfoUser.setId(user.getInfoUser().getId());
						saveinfoUser.setTotalLost("0");
						saveinfoUser.setPeoplewin("0");
						saveinfoUser.setBalance("0");
						saveinfoUser.setPay("0");
						saveinfoUser.setNotpay("0");
						infouserRepo.save(saveinfoUser);
						successAndFaiModal.setStatusSuccess(true);
						successAndFaiModal.setMessage(ConstantData.MESSAGE_SUCCESS);
						// listNumberRepo.updateStatusInsert(ConstantData.MESSAGE_SUCCESS,joinNumber,NumRequest.getPrice(),all_price.toString(),NumRequest.getOption(),NumRequest.getDate()
						// ,ConstantData.MESSAGE_NO,user.getInfoUser().getId(),"unLucky",NumRequest.getLuckytime(),ConstantData.MESSAGE_N);

					}
					if (!status_Update) {
						successAndFaiModal.setMessage(ConstantData.MESSAGE_UNALNE_TO_INSERT_NUMBER);
					}
				}
			}

//

			System.out.println("check count_win = " + count_win);
			System.out.println("check total_purchase = " + total_purchase);

		} catch (Exception e) {

			throw new Exception("Error Lottary Service = " + e);
		}

		return successAndFaiModal;

	}

	public Boolean postUpdateStatusPaymentService(listNumberRquestModal listRequest, UserModal userModal) {
		Boolean updateStatus = false;
		List_number_Modal dataItem = null;
		String id = listRequest.getId();
		String statusPayment = listRequest.getStatuspayment();
		InfoUserModal infoUser = null;
		String luckyTime = listRequest.getLuckytime();
		String replaceReward = listRequest.getReward().replace(",", "");
		Integer pay = 0;
		Integer reduceNotpay = 0;
		String strPay  = "";
		String strNotPay = "";
		try {
			infoUser = infouserRepo.findInfoUser(id, luckyTime);
			String replaceNotPay = infoUser.getNotpay().replace(",", "");
			String replaceTotalLost = infoUser.getTotalLost().replace(",", "");
			String replacePay = infoUser.getPay().replace(",", "");

			if (statusPayment.equals(ConstantData.MESSAGE_YES)) {
				pay = Integer.valueOf(replaceReward) + Integer.valueOf(replacePay);
				if(replaceNotPay.length() >0)
				{
					reduceNotpay = Integer.valueOf(replaceNotPay) - Integer.valueOf(replaceReward);
				}
				else {
					reduceNotpay = Integer.valueOf(replaceTotalLost) - Integer.valueOf(replaceReward);
				}
			
				strPay= numberFormatter.format(pay);
				strNotPay = numberFormatter.format(reduceNotpay);
				
//				reduceNotpay = reduceNotpay - Integer.valueOf(infoUser.getNotpay());
			} else {
				pay = Integer.valueOf(replacePay) - Integer.valueOf(replaceReward);
				if(replaceNotPay.length() >0)
				{
					reduceNotpay = Integer.valueOf(replaceNotPay) + Integer.valueOf(replaceReward);
				}
				else {
					reduceNotpay = Integer.valueOf(replaceTotalLost);
				}
				strPay= numberFormatter.format(pay);
				strNotPay = numberFormatter.format(reduceNotpay);
			}

//			dataItem = listNumberRepo.findEachItem(id, luckyDate);

			int resultUpdatePayment = listNumberRepo.postUpdateStatusPaymentRepo(statusPayment, id,
					listRequest.getIdlist(), luckyTime);
			int resultUpdateInfoUser = infouserRepo.updateInfoUserPayNotPay(strPay, strNotPay, id,
					luckyTime);
			if (resultUpdatePayment > 0 && resultUpdateInfoUser > 0) {
				updateStatus = true;
			}

		} catch (Exception e) {
			throw e;
		}
		return updateStatus;
	}

	public LottaryModal getLuckyItemService() {
		LottaryModal item = new LottaryModal();

		try {

			item = lottaryRepo.findLastDate();

		} catch (Exception e) {
			throw e;
		}
		return item;
	}

	public LottaryModal postLuckyItemService(String luckyDate) {
		LottaryModal item = new LottaryModal();

		try {

			item = lottaryRepo.findDate(luckyDate);

		} catch (Exception e) {
			throw e;
		}
		return item;
	}

	public SuccessAndFailModal validateLuckyItemService(String luckydate, String id) {
		List<List_number_Modal> dataItem = null;
		Boolean status = false;
		LottaryModal luckyDate = new LottaryModal();
		ArrayList<String> idlottary = new ArrayList<>();
		ArrayList<String> idValidateLottary = new ArrayList<>();
		HashMap<String, String> rewardAndId = new HashMap<String, String>();
		InfoUserModal user = new InfoUserModal();
		int allTotalLost = 0;
		String statusTransfer = null;
		NumberFormat numberFormatter = NumberFormat.getInstance(Locale.getDefault());
		// String noLuckySequence = "0";
		SuccessAndFailModal process = new SuccessAndFailModal();
		process.setStatusSuccess(false);
		Set<String> setLucky = new LinkedHashSet<>();
		Set<String> setNoLucky = new LinkedHashSet<>();
		try {
			user = infouserRepo.findInfoUser(id, luckydate);
			statusTransfer = user.getStatusTransfer();
			if (statusTransfer.equals(ConstantData.MESSAGE_N)) {
				luckyDate = lottaryRepo.findDate(luckydate);
				String[] threeDown = luckyDate.getThreedow().split(",");
				System.out.println(ArrayUtils.contains(threeDown, "20"));
				System.out.println(ArrayUtils.contains(threeDown, "123"));

				if (null != luckyDate) {
					dataItem = listNumberRepo.findItembyStatusTransfer(id, luckydate);
					PriceLottaryModal priceLottary = priceLottaryRepo.getPrice();
					Integer convPriceThreeBath = Integer.valueOf(priceLottary.getThreeBath());
					Integer convPriceTwoBath = Integer.valueOf(priceLottary.getTwoBath());

					if (null != dataItem && dataItem.size() > 0) {
						for (List_number_Modal item : dataItem) {
							idValidateLottary.add(item.getIdlist());
							String number = item.getNumber();
							String[] split_number = number.split(",");
							Integer price = Integer.valueOf(item.getPrice());
							for (String no : split_number) {
								Integer sum = 0;
								if (item.getOptinpurchase().equals(ConstantData.MESSAGE_TOP)) {
									if (no.equals(luckyDate.getThreeTop())) {

//										listNumberRepo.changeStatusLucky(id,idLottary,date);
										idlottary.add(item.getIdlist());
										sum = price * convPriceThreeBath;
										setLucky.add(item.getSequence());
										rewardAndId.put(item.getIdlist(), sum.toString());

									} else if (no.equals(luckyDate.getTwotop())) {
										idlottary.add(item.getIdlist());
										sum = price * convPriceTwoBath;
										setLucky.add(item.getSequence());
										rewardAndId.put(item.getIdlist(), sum.toString());

									} else {
										setNoLucky.add(item.getSequence());
									}
									allTotalLost = allTotalLost + sum;
								} else if (item.getOptinpurchase().equals(ConstantData.MESSAGE_BELOW)) {

									if (no.equals(luckyDate.getTwodown())) {

										idlottary.add(item.getIdlist());
										sum = price * convPriceTwoBath;
										setLucky.add(item.getSequence());
										rewardAndId.put(item.getIdlist(), sum.toString());

									} else if (ArrayUtils.contains(threeDown, no)) {
										idlottary.add(item.getIdlist());
										sum = price * convPriceThreeBath;
										setLucky.add(item.getSequence());
										rewardAndId.put(item.getIdlist(), sum.toString());

									} else {
										setNoLucky.add(item.getSequence());
									}
									allTotalLost = allTotalLost + sum;
								}
							}
						}
						int peopleWin = 0;
						for (String data : setLucky) {
							boolean contain = setNoLucky.contains(data);
							if (contain) {
								peopleWin = peopleWin + 1;
								setNoLucky.remove(data);

							} else {
								peopleWin = peopleWin + 1;
							}

						}

						if (null != idlottary && idlottary.size() > 0) {
							status = true;

						}

						if (status) {
							String toTalPurchaseRepalce = user.getTotalPurchase().replace(",", "");
							int toTalPurchaseInt = Integer.parseInt(toTalPurchaseRepalce);
							int Balance = toTalPurchaseInt - allTotalLost;
							String conBalance = numberFormatter.format(Balance);
							String peopleLost = numberFormatter.format(setNoLucky.size());
							String peopleWinCon = numberFormatter.format(peopleWin);
							String conAllTotalLost = numberFormatter.format(allTotalLost);
							infouserRepo.updateInfoPeopleLostWinTotalLost(peopleLost, peopleWinCon, conAllTotalLost,
									conAllTotalLost,conAllTotalLost, conBalance, luckydate, id);
							listNumberRepo.changeStatusValidate(id, idValidateLottary, luckydate);
							listNumberRepo.changeStatusLucky(id, idlottary, luckydate);
							for (String idList : rewardAndId.keySet()) {
								listNumberRepo.updateReward(rewardAndId.get(idList), idList, luckydate);
							}
							process.setMessage(ConstantData.MESSAGE_SUCCESS_TH);
							process.setStatusSuccess(status);
						} else {
							process.setMessage(ConstantData.MESSAGE_NO_DATA_TH);
						}

					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return process;
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
		Boolean statusValidate = false;
		Integer sum = 0;
		String date = "";
		Integer oldMonth = 0;
		Integer allSum = 0;
		MonthModal monthModal = new MonthModal();
		MonthModal monthMoal2 = new MonthModal();
		LottaryModal luckytime = new LottaryModal();
		List<InfoUserModal> dataTransfer = new ArrayList<>();
		TransferLottaryModal itemTransfer = new TransferLottaryModal();
		List<List_number_Modal> listItem = new ArrayList<>();
		InfoUserModal infoUser = new InfoUserModal();
		MixTransferListNumberModal mixTransferNumber = new MixTransferListNumberModal();
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
		try {
			dataTransfer = infouserRepo.finInfoUserTransfer(id, listRequest.getLuckytime(), nickname);
			if (null != dataTransfer && dataTransfer.size() > 1) {
				duplicateTransfer = true;
			} else {
				luckytime = lottaryRepo.findByDate(listRequest.getLuckytime());
				if (null != luckytime) {

					listItem = listNumberRepo.getItemListNumber(id, listRequest.getLuckytime());
					if (listItem.size() > 0 && listItem != null) {
						for (List_number_Modal data : listItem) {
							if (data.getStatusValidate().equals(ConstantData.MESSAGE_N)) {
								statusValidate = true;
								break;
							}
						}
						if (!statusValidate) {

							infoUser = infouserRepo.findInfoUser(id, listRequest.getLuckytime());
							Integer convert_Total_Purchase = Integer.valueOf(infoUser.getTotalPurchase());
							Integer convert_Total_lost = Integer.valueOf(infoUser.getTotalLost());
							sum = convert_Total_Purchase - convert_Total_lost;
							date = infoUser.getDate();
							String[] split_date = date.split("-");
							String year = split_date[0];
							String month = split_date[1];
							monthModal = monthRepo.findMonthOfUser(id, year);
							if (null != monthModal) {
								if (month.equals("01")) {
									if (null != monthModal.getJan() || monthModal.getJan().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getJan());
										allSum = oldMonth + sum;
										monthRepo.updateMonth1(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth1(String.valueOf(sum), id, year);
									}

								} else if (month.equals("02")) {
									if (null != monthModal.getFeb() || monthModal.getFeb().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getFeb());
										allSum = oldMonth + sum;
										monthRepo.updateMonth2(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth2(String.valueOf(sum), id, year);
									}
								} else if (month.equals("03")) {
									if (null != monthModal.getMar() || monthModal.getMar().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getMar());
										allSum = oldMonth + sum;
										monthRepo.updateMonth3(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth3(String.valueOf(sum), id, year);
									}
								} else if (month.equals("04")) {
									if (null != monthModal.getApr() || monthModal.getApr().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getApr());
										allSum = oldMonth + sum;
										monthRepo.updateMonth4(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth4(String.valueOf(sum), id, year);
									}

								} else if (month.equals("05")) {
									if (null != monthModal.getMay() || monthModal.getMay().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getMay());
										allSum = oldMonth + sum;
										monthRepo.updateMonth5(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth5(String.valueOf(sum), id, year);
									}
								} else if (month.equals("06")) {
									if (null != monthModal.getJun() || monthModal.getJun().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getJun());
										allSum = oldMonth + sum;
										monthRepo.updateMonth6(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth6(String.valueOf(sum), id, year);
									}
								} else if (month.equals("07")) {
									if (null != monthModal.getJul() || monthModal.getJul().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getJul());
										allSum = oldMonth + sum;
										monthRepo.updateMonth7(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth7(String.valueOf(sum), id, year);
									}
								} else if (month.equals("08")) {
									if (null != monthModal.getAug() || monthModal.getAug().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getAug());
										allSum = oldMonth + sum;
										monthRepo.updateMonth8(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth8(String.valueOf(sum), id, year);
									}
								} else if (month.equals("09")) {
									if (null != monthModal.getSep() || monthModal.getSep().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getSep());
										allSum = oldMonth + sum;
										monthRepo.updateMonth9(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth9(String.valueOf(sum), id, year);
									}
								} else if (month.equals("10")) {
									if (null != monthModal.getOct() || monthModal.getOct().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getOct());
										allSum = oldMonth + sum;
										monthRepo.updateMonth10(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth10(String.valueOf(sum), id, year);
									}
								} else if (month.equals("11")) {
									if (null != monthModal.getNov() || monthModal.getNov().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getNov());
										allSum = oldMonth + sum;
										monthRepo.updateMonth11(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth11(String.valueOf(sum), id, year);
									}
								} else {
									if (null != monthModal.getDecem() || monthModal.getDecem().equals('0')) {
										oldMonth = Integer.valueOf(monthModal.getDecem());
										allSum = oldMonth + sum;
										monthRepo.updateMonth12(String.valueOf(allSum), id, year);

									} else {
										monthRepo.updateMonth12(String.valueOf(sum), id, year);
									}
								}
								transferStatus = true;
							} else {
								if (month.equals("01")) {
									monthMoal2.setJan(String.valueOf(sum));
								} else if (month.equals("02")) {
									monthMoal2.setFeb(String.valueOf(sum));
								} else if (month.equals("03")) {
									monthMoal2.setMar(String.valueOf(sum));
								} else if (month.equals("04")) {
									monthMoal2.setApr(String.valueOf(sum));
								} else if (month.equals("05")) {
									monthMoal2.setMay(String.valueOf(sum));
								} else if (month.equals("06")) {
									monthMoal2.setJun(String.valueOf(sum));
								} else if (month.equals("07")) {
									monthMoal2.setJul(String.valueOf(sum));
								} else if (month.equals("08")) {
									monthMoal2.setAug(String.valueOf(sum));
								} else if (month.equals("09")) {
									monthMoal2.setSep(String.valueOf(sum));
								} else if (month.equals("10")) {
									monthMoal2.setOct(String.valueOf(sum));
								} else if (month.equals("11")) {
									monthMoal2.setNov(String.valueOf(sum));
								} else {
									monthMoal2.setDecem(String.valueOf(sum));
								}
//								monthRepo.save(monthMoal2);
								monthRepo.insertMonth(monthMoal2.getJan(), monthMoal2.getFeb(), monthMoal2.getMar(),
										monthMoal2.getApr(), monthMoal2.getMay(), monthMoal2.getJun(),
										monthMoal2.getJul(), monthMoal2.getAug(), monthMoal2.getSep(),
										monthMoal2.getOct(), monthMoal2.getNov(), monthMoal2.getDecem(), id, year);
								transferStatus = true;
							}
							if (transferStatus) {
								infouserRepo.updateTotalPurchase(String.valueOf(sum), id, nickname,
										listRequest.getLuckytime(), formattedDate);
//								itemTransfer.setIduser(iduser);
//								itemTransfer.setNickname(nickname);
//								itemTransfer.setDate(listRequest.getLuckytime());
//								itemTransfer.setTimeTransfer(formattedDate);
//								itemTransfer.setBalance(String.valueOf(sum));
//								itemTransfer.setTotalPurchase(infoUser.getTotalPurchase());
//								itemTransfer.setTotalLost(infoUser.getTotalLost());
//								transferLottaryRepo.save(itemTransfer);
								listNumberRepo.postSendLottaryRepo(id, listRequest.getLuckytime());

							}
						}
					}
				}

			}

			mixTransferNumber.setDuplicateTransfer(duplicateTransfer);
			mixTransferNumber.setListNumberModal(listItem);
			mixTransferNumber.setLuckytime(luckytime);
			mixTransferNumber.setStatusValidate(statusValidate);

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
