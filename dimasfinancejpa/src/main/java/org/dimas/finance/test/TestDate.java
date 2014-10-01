package org.dimas.finance.test;

import java.util.Calendar;
import java.util.Date;

import org.dimas.finance.util.SysvarHelper;

public class TestDate {
	
	public static void main(String [] args){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		Date tgl = new Date(new Date().getYear(), 0, 1);
		
		
		SysvarHelper helper=new SysvarHelper();
		helper.cekOrCreateNewConfigSysvar("");
		
		System.out.println(tgl);
		
	}
	
	
}
