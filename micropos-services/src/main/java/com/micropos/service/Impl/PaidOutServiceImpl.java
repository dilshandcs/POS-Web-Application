package com.micropos.service.Impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micropos.dao.PaidOutDao;
import com.micropos.domain.PaidOut;
import com.micropos.dto.PaidOutDto;
import com.micropos.service.PaidOutService;

@Service
public class PaidOutServiceImpl implements PaidOutService{
	@Autowired
	PaidOutDao paidOutDao;

	@Transactional
	public void SavePaidOut(PaidOutDto paidOutDto) {
		PaidOut paidOut=new PaidOut();
		paidOut.setPaidoutItem(paidOutDto.getPaidOutItem());
		paidOut.setDescription(paidOutDto.getDescriptionP());
		paidOut.setAmount(paidOutDto.getAmountP());
		paidOut.setDate(new Date());

		paidOutDao.SavePaidOut(paidOut);
	}

}
