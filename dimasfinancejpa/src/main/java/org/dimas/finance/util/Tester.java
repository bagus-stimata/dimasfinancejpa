package org.dimas.finance.util;

import java.util.Calendar;

import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaService;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;

public class Tester {
	private SysvarJpaService sysvarService;
	private ArPaymentHeaderJpaService arpaymentHeaderService;
	private TransaksiHelper managerTransaksi;
	
	public Tester(){
		sysvarService = new SysvarJpaServiceImpl();
		arpaymentHeaderService = new ArPaymentHeaderJpaServiceImpl();
		managerTransaksi = new TransaksiHelper();
		SysvarHelper sysvarHelper = new SysvarHelper();
		
		
		sysvarHelper.cekOrCreateNewConfigSysvar("");
		
		
//		System.out.println(managerTransaksi.getNewNomorUrutArPayment());
//		System.out.println(managerTransaksi.getCurrentNomorUrutBukuGiro());
//		System.out.println(managerTransaksi.getNewNomorUrutBukuGiro());
		
		
	}
	
	public static void main(String [] args){
//		Tester f = new Tester();
		
			    Calendar now = Calendar.getInstance();
			    now.set(Calendar.YEAR, 2014);
			    now.set(Calendar.MONTH, 0);
			    now.set(Calendar.DAY_OF_MONTH, 5);
			    
			    System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) + "-"
			        + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));
			    
			    
			    String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
			        "Friday", "Saturday" };
			    // Day_OF_WEEK starts from 1 while array index starts from 0
			    System.out.println("Current day is : " + strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
			    System.out.println(now.get(Calendar.DAY_OF_WEEK));
	}
}
