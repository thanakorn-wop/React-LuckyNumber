package BackendLuckyNumber.Backend.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.InfoAdminModal;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.LottaryModal;
import BackendLuckyNumber.Backend.Modal.SuccessAndFailModal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.Repo.InfoAdminRepo;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;
import BackendLuckyNumber.Backend.Repo.TransferLottaryRepo;
import BackendLuckyNumber.Backend.RequestModel.InfoUserReqModal;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;
import BackendLuckyNumber.Backend.RequestModel.UserdetailsIml;

@Service
public class ReportService {

	@Autowired
	TransferLottaryRepo transferlottaryRepo;
	@Autowired
	LottaryRepo lottaryRepo;

	@Autowired
	InfoUserRepo infouserRepo;
	
	@Autowired
	InfoAdminRepo infoAdminRepo;

	private NumberFormat numberFormatter = NumberFormat.getInstance(Locale.getDefault()); 
	public List<InfoUserModal> getAllUserService() {
		List<InfoUserModal> dataTransfer = null;

		try {
			dataTransfer = infouserRepo.getAllUser();
		} catch (Exception e) {
			throw e;
		}

		return dataTransfer;
	}

	public SuccessAndFailModal postConfirmService(InfoUserReqModal req,	UserdetailsIml user) {
		String id = req.getId();
		String nickName = req.getNickname();
		String date = req.getDate();
		UserModal userLogin = user.getInfoUser();
		String idUser = userLogin.getIduser();
		Boolean update = false;
		InfoUserModal dataTransfer = null;
		int result = 0;
		int sum = 0;
		InfoAdminModal admin = new InfoAdminModal();
		SuccessAndFailModal process = new SuccessAndFailModal();
		process.setStatusSuccess(false);
		try {
			dataTransfer = infouserRepo.getOnlyUser(id, date, nickName,req.getHead());
			if (null != dataTransfer) {
				if( !dataTransfer.getStatusTransfer().equals(ConstantData.MESSAGE_N))
				{
					result = infouserRepo.updateDoneInfoUser(ConstantData.MESSAGE_Y, id, date, nickName);
					if (result > 0) {
						update = true;
						process.setStatusSuccess(update);
						process.setMessage(ConstantData.MESSAGE_SUCCESS_TH);
						process.setStatusMessage(ConstantData.ALERT_MESSAGE_SUCCESS);
						admin = infoAdminRepo.findInfoAdmin(idUser, date, userLogin.getNickname());
						if(null != admin)
						{
							
							Integer convertTotalPurchase = Integer.valueOf(admin.getTotalPurchase().replace(",", ""));
							Integer convertTotalLost= Integer.valueOf(admin.getTotalLost().replace(",", ""));
							Integer convertPeopleWin = Integer.valueOf(admin.getPeoplewin().replace(",", ""));
							Integer convertPeopleLost = Integer.valueOf(admin.getPeoplelost().replace(",", ""));
							Integer convertBalance = Integer.valueOf(admin.getBalance().replace(",", ""));
							Integer convertPay= Integer.valueOf(admin.getPay().replace(",", ""));
							Integer convertNotPay = Integer.valueOf(admin.getNotpay().replace(",", ""));
							Integer sumTotalPurchase = convertTotalPurchase+Integer.valueOf(req.getTotalPurchase().replace(",", ""));
							Integer sumTotalLost = convertTotalLost+Integer.valueOf(req.getTotalLost().replace(",", ""));
							Integer sumPeopleWin = convertPeopleWin+Integer.valueOf(req.getPeoplewin().replace(",", ""));
							Integer sumPeopleLost = convertPeopleLost+Integer.valueOf(req.getPeoplelost().replace(",", ""));
							Integer sumBalance = convertBalance+Integer.valueOf(req.getBalance().replace(",", ""));
							Integer sumPay = convertPay+Integer.valueOf(req.getPay().replace(",", ""));
							Integer sumNotPay = convertNotPay+Integer.valueOf(req.getNotpay().replace(",", ""));
							int resultUpdate = infoAdminRepo.updateInfoAdmin(numberFormatter.format(sumBalance),numberFormatter.format(sumTotalPurchase),numberFormatter.format(sumTotalLost)
									, numberFormatter.format(sumPeopleWin), numberFormatter.format(sumPeopleLost),
									numberFormatter.format(sumPay),numberFormatter.format(sumNotPay),
									req.getDate(),userLogin.getIduser(),userLogin.getNickname());
							if(resultUpdate <0)
							{
								update = false;
								process.setStatusSuccess(update);
								process.setMessage(ConstantData.MESSAGE_NOT_SUCCESS_TH);
								process.setStatusMessage(ConstantData.ALERT_MESSAGE_SUCCESS);
							}
						}else {
//							(total_purchase,total_lost,people_win,people_lost,pay,notpay,date)
							 result = infoAdminRepo.insertAdmin(req.getBalance(),req.getTotalPurchase(),req.getTotalLost(),
									 req.getPeoplewin(),req.getPeoplelost(),
									 req.getPay(),req.getNotpay(),req.getDate(),idUser,userLogin.getNickname());
							if(result <0)
							{
								update = false;
								process.setStatusSuccess(update);
								process.setMessage(ConstantData.MESSAGE_NOT_SUCCESS_TH);
								process.setStatusMessage(ConstantData.ALERT_MESSAGE_ERROR);
							}
						}
						
						
						
					}
					else {
						process.setMessage(ConstantData.MESSAGE_NOT_SUCCESS_TH);
						process.setStatusMessage(ConstantData.ALERT_MESSAGE_ERROR);
					}
				}
				else {
					process.setMessage(ConstantData.MESSAGE_BEFORE_TRANSFER_TH);
					process.setStatusMessage(ConstantData.ALERT_MESSAGE_ERROR);
				}
				
			}
			else {
				process.setMessage(ConstantData.MESSAGE_NO_DATA_TH);
				process.setStatusMessage(ConstantData.ALERT_MESSAGE_ERROR);
			}

		} catch (Exception e) {
			throw e;
		}

		return process;
	}

	public Boolean postInsertNumberLuckyReportService(LuckyNumberReq luckyNumberReq, String userUpdate)
			throws Exception {
		Boolean status = false;
		String regex = "[,' ']";
		String CovnertThreeDown = luckyNumberReq.getThreedown().trim().replaceAll(regex, ",");
		LottaryModal lottary = new LottaryModal();
		lottary.setDate(luckyNumberReq.getDate());
		lottary.setThreedow(CovnertThreeDown);
		lottary.setThreeTop(luckyNumberReq.getThreetop());
		lottary.setTwodown(luckyNumberReq.getTwodown());
		lottary.setTwotop(luckyNumberReq.getTwotop());
		lottary.setBiglucky(luckyNumberReq.getBiglucky());
		lottary.setStatusLottary("Y");
		lottary.setUserUpdate(userUpdate);
		try {
			LottaryModal dataLottary = lottaryRepo.findByDate(luckyNumberReq.getDate());
			if (null != dataLottary) {
				System.out.println("Duplicate date ");
				status = false;
			} else {
				lottaryRepo.save(lottary);
				status = true;
			}

		} catch (Exception e) {
			throw new Exception("ERROR  Lottary is = " + e);

		}
		return status;
	}

	public Boolean postUpdateLuckyNumberReportService(LuckyNumberReq luckyNumberReq) {
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

}
