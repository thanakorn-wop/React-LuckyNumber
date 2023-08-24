package BackendLuckyNumber.Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendLuckyNumber.Backend.Constant.ConstantData;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.LottaryModal;
import BackendLuckyNumber.Backend.Modal.SuccessAndFailModal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
import BackendLuckyNumber.Backend.Repo.InfoUserRepo;
import BackendLuckyNumber.Backend.Repo.LottaryRepo;
import BackendLuckyNumber.Backend.Repo.TransferLottaryRepo;
import BackendLuckyNumber.Backend.RequestModel.InfoUserReqModal;
import BackendLuckyNumber.Backend.RequestModel.LuckyNumberReq;

@Service
public class ReportService {

	@Autowired
	TransferLottaryRepo transferlottaryRepo;
	@Autowired
	LottaryRepo lottaryRepo;

	@Autowired
	InfoUserRepo infouserRepo;

	public List<InfoUserModal> getAllUserService() {
		List<InfoUserModal> dataTransfer = null;

		try {
			dataTransfer = infouserRepo.getAllUser();
		} catch (Exception e) {
			throw e;
		}

		return dataTransfer;
	}

	public SuccessAndFailModal postConfirmService(InfoUserReqModal req) {
		String id = req.getId();
		String nickName = req.getNickname();
		String date = req.getDate();
		Boolean update = false;
		InfoUserModal dataTransfer = null;
		int result = 0;
		SuccessAndFailModal process = new SuccessAndFailModal();
		process.setStatusSuccess(false);
		try {
			dataTransfer = infouserRepo.getOnlyUser(id, date, nickName);
			if (null != dataTransfer) {
				if( dataTransfer.getDone().equals(ConstantData.MESSAGE_N))
				{
					result = infouserRepo.updateDoneInfoUser(ConstantData.MESSAGE_Y, id, date, nickName);
					if (result > 0) {
						update = true;
						process.setStatusSuccess(true);
						process.setMessage(ConstantData.MESSAGE_SUCCESS_TH);
						
					}
					else {
						process.setMessage(ConstantData.MESSAGE_NOT_SUCCESS_TH);
						process.setStatusMessage(ConstantData.ALERT_MESSAGE_ERROR);
					}
				}
				else {
					process.setMessage(ConstantData.MESSAGE_NOT_SUCCESS_TH);
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
