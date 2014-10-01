package org.dimas.finance.temp;

import java.util.ArrayList;
import java.util.List;

import org.dimas.finance.jpa.dao.ArPaymentDetailJpaService;
import org.dimas.finance.jpa.dao.ArPaymentDetailJpaServiceImpl;
import org.dimas.finance.model.Arpaymentdetail;

public class TestJpa {

	public static void main(String [] args){
		System.out.println("ehehehe");
		ArPaymentDetailJpaService testService = new ArPaymentDetailJpaServiceImpl();
		List<Arpaymentdetail> list = new ArrayList<Arpaymentdetail>();
		list = testService.findAll();
		for (Arpaymentdetail item: list){
			System.out.println(item.getCashamountpay());
		}
		
	}
}
