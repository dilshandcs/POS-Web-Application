package com.micropos.controller;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micropos.dto.PaidOutDto;
import com.micropos.dto.SoldItemDto;
import com.micropos.dto.SoldItemDtoList;
import com.micropos.properties.MicroPOSResponse;
import com.micropos.service.PaidOutService;
import com.micropos.service.SalesManagementService;

@Controller
@RequestMapping(value="/portal")
public class TransactionController implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	SalesManagementService salesManagementService;

	@Autowired
	PaidOutService paidOutService;

	@Autowired
	SerialClass serialClass;

	@Autowired
	SerialClass serialClass2;

	@Autowired
	SerialClass serialClass3;

	@Autowired
	SerialClass serialClass4;

	public static BufferedReader input;
	public static OutputStream output;

	@RequestMapping(value="/fullpayment", method = RequestMethod.POST)
	@ResponseBody
	public MicroPOSResponse SaveTransaction(@ModelAttribute SoldItemDtoList soldItemDtoList) {
List<SoldItemDto> soldItemDtos=soldItemDtoList.getSoldItemDtos();

try {

			Double balance=salesManagementService.SaveFullTransaction(soldItemDtos, soldItemDtoList.getPaidAmount(), soldItemDtoList.getCusName(),soldItemDtoList.getTotal());
			String title="Balance is "+balance;

/*			serialClass.OpenDrawer();
*/
			return new MicroPOSResponse(null, "Payment is Successfull", true, title);
		}
		catch(Exception e) {
			return new MicroPOSResponse(null, e.getMessage(), false, "Info");
		}

	}

	@RequestMapping(value="/advancepayment", method = RequestMethod.POST)
	@ResponseBody
	public MicroPOSResponse AdvancePayment(@ModelAttribute SoldItemDtoList soldItemDtoList) {
List<SoldItemDto> soldItemDtos=soldItemDtoList.getSoldItemDtos();

try {

			salesManagementService.SaveAdvanceTransaction(soldItemDtos, soldItemDtoList.getPaidAmount(), soldItemDtoList.getCusName(),soldItemDtoList.getTotal());
			String title="Balance is 0.00";

/*			serialClass2.OpenDrawer();*/

			return new MicroPOSResponse(null, "Payment is Successfull", true, title);
		}
		catch(Exception e) {
			return new MicroPOSResponse(null, e.getMessage(), false, "Info");
		}

	}




	@RequestMapping(value="/dummyBillPrint", method = RequestMethod.POST)
	@ResponseBody
	public MicroPOSResponse DummyBillPrint(@ModelAttribute SoldItemDtoList soldItemDtoList) {
		List<SoldItemDto> soldItemDtos=soldItemDtoList.getSoldItemDtos();

		try {

			String invoiceNo=salesManagementService.SaveTransaction(soldItemDtos, soldItemDtoList.getPaidAmount(), soldItemDtoList.getCusName(),soldItemDtoList.getTotal());
			return new MicroPOSResponse(invoiceNo, "Dummy Bill is Created Successfully", true, "Success");
		}
		catch(Exception e) {
			return new MicroPOSResponse(null, e.getMessage(), false, "Info");
		}

	}

	@RequestMapping(value="/completeAdvance", method = RequestMethod.POST)
	@ResponseBody
	public MicroPOSResponse CompleteAdvance(@RequestParam(value="invoiceId") Integer invoiceId) {

try {
			salesManagementService.CompleteAdvance(invoiceId);
/*			serialClass3.OpenDrawer();
*/			return new MicroPOSResponse(null, "Successfully Completed the Advance", true, "Successfull");
		}
		catch(Exception e) {
			return new MicroPOSResponse(null, e.getMessage(), false, "Info");
		}

	}

	@RequestMapping(value="/savePaidout", method = RequestMethod.POST)
	@ResponseBody
	public MicroPOSResponse PaidOut(@ModelAttribute(value="paidOutDto") PaidOutDto paidOutDto) {

		try {
			paidOutService.SavePaidOut(paidOutDto);
/*			serialClass4.OpenDrawer();
*/			return new MicroPOSResponse(null, "Successfully Completed the Advance", true, "Successfull");
		}
		catch(Exception e) {
			return new MicroPOSResponse(null, e.getMessage(), false, "Info");
		}

	}



}
