package com.micropos.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micropos.dao.PaidOutDao;
import com.micropos.domain.PaidOut;
import com.micropos.dto.CashFLowDataLists;
import com.micropos.dto.PaidOutDto;
import com.micropos.dto.PastPaidOutsDtoList;
import com.micropos.dto.PastPaidOutsFilterReportDto;
import com.micropos.service.PastPaidOutService;

@Service
public class PastPaidOutServiceImpl implements PastPaidOutService {

	@Autowired
	PaidOutDao paidOutDao;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	@Transactional
	public List<PaidOutDto> getDataForPaidOutsReport() {
		List<PaidOut> paidOutList=paidOutDao.getAllPaidOuts();

		List<PaidOutDto> pastPaidOutDto=new ArrayList<PaidOutDto>();

		for(PaidOut paidout:paidOutList) {
			PaidOutDto paidOutDto=new PaidOutDto();
			paidOutDto.setDescriptionP(paidout.getDescription());
			paidOutDto.setPaidOutItem(paidout.getPaidoutItem());
			paidOutDto.setAmountP(paidout.getAmount());
			paidOutDto.setDate(paidout.getDate());
			pastPaidOutDto.add(paidOutDto);
		}
		pastPaidOutDto.sort(new Comparator<PaidOutDto>() {
			public int compare(PaidOutDto o1, PaidOutDto o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});

		return pastPaidOutDto;
	}

	@Transactional
	public List<PastPaidOutsDtoList> getPaidOutsForReport(PastPaidOutsFilterReportDto pastPaidOutsFilterReportDto) {
		List<PaidOut> paidOutList=paidOutDao.getAllPaidOuts();

		try {
			Calendar c = Calendar.getInstance();

			Date fromDate;
			Date toDate;

			if(pastPaidOutsFilterReportDto.getFromDate()!=null && !pastPaidOutsFilterReportDto.getFromDate().isEmpty() && pastPaidOutsFilterReportDto.getToDate()!=null && !pastPaidOutsFilterReportDto.getToDate().isEmpty()) {
				c.setTime(sdf1.parse(pastPaidOutsFilterReportDto.getFromDate()));
				fromDate = c.getTime();

				c.setTime(sdf1.parse(pastPaidOutsFilterReportDto.getToDate()));
				toDate = c.getTime();

			}else {
				String[] from = pastPaidOutsFilterReportDto.getStartDate().split("/"); // get year and month from

				c.set(Integer.parseInt(from[0]), Integer.parseInt(from[1]) - 1, 1);
				 fromDate = c.getTime();


				toDate = new Date();
			}

			Map<String,List<PaidOutDto>> map=new HashMap<String, List<PaidOutDto>>();

			for(PaidOut paidout:paidOutList) {

				if (paidout.getDate().getTime() < toDate.getTime()+86400000 && paidout.getDate().getTime()>fromDate.getTime()){

				PaidOutDto paidOutDto=new PaidOutDto();
				paidOutDto.setDescriptionP(paidout.getDescription());
				paidOutDto.setPaidOutItem(paidout.getPaidoutItem());
				paidOutDto.setAmountP(paidout.getAmount());
				paidOutDto.setDates(sdf.format(paidout.getDate()));

				List<PaidOutDto> paidOutDtoLst=map.get(paidOutDto.getDates());

				if(paidOutDtoLst==null) {
					paidOutDtoLst=new ArrayList<PaidOutDto>();
					paidOutDtoLst.add(paidOutDto);
					map.put(paidOutDto.getDates(), paidOutDtoLst);
				}
				else
				{
					paidOutDtoLst.add(paidOutDto);
					map.put(paidOutDto.getDates(), paidOutDtoLst);
				}

			}

			}

			TreeMap<String, List<PaidOutDto>> maps=new TreeMap<String, List<PaidOutDto>>(map);

			List<PastPaidOutsDtoList> pastPaidOutsDtoList=new ArrayList<PastPaidOutsDtoList>();

			for(Entry<String,List<PaidOutDto>> entry : maps.entrySet()){
				PastPaidOutsDtoList pastPaidOutsDto=new PastPaidOutsDtoList();
				pastPaidOutsDto.setDate(entry.getKey());
				pastPaidOutsDto.setPaidOutDtoList(entry.getValue());

				pastPaidOutsDtoList.add(pastPaidOutsDto);
			}
			pastPaidOutsDtoList.sort(new Comparator<PastPaidOutsDtoList>() {
				public int compare(PastPaidOutsDtoList o1, PastPaidOutsDtoList o2) {
					return o2.getDate().compareTo(o1.getDate());
				}
			});

			return pastPaidOutsDtoList;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CashFLowDataLists getCashFlowData() {
		// TODO Auto-generated method stub
		return null;
	}

}
