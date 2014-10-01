package org.dimas.finance.test;

import org.dimas.finance.jpa.dao.BBankDetailJpaService;
import org.dimas.finance.jpa.dao.BBankDetailJpaServiceImpl;
import org.dimas.finance.jpa.dao.BBankHeaderJpaService;
import org.dimas.finance.jpa.dao.BBankHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkbDetailJpaService;
import org.dimas.finance.jpa.dao.BkbDetailJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkbHeaderJpaService;
import org.dimas.finance.jpa.dao.BkbHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkkDetailJpaService;
import org.dimas.finance.jpa.dao.BkkDetailJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkkHeaderJpaService;
import org.dimas.finance.jpa.dao.BkkHeaderJpaServiceImpl;
import org.dimas.finance.model.Account;
import org.dimas.finance.model.Bbankdetail;
import org.dimas.finance.model.BbankdetailPK;
import org.dimas.finance.model.Bbankheader;
import org.dimas.finance.model.BbankheaderPK;
import org.dimas.finance.model.Bkbdetail;
import org.dimas.finance.model.BkbdetailPK;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.BkbheaderPK;
import org.dimas.finance.model.Bkkdetail;
import org.dimas.finance.model.BkkdetailPK;
import org.dimas.finance.model.Bkkheader;
import org.dimas.finance.model.BkkheaderPK;
import org.dimas.finance.model.Customer;
import org.dimas.finance.model.CustomerPK;
import org.dimas.finance.model.Division;

public class TestModel {
	private BkbHeaderJpaService bkbheaderService = new BkbHeaderJpaServiceImpl();
	private BkbDetailJpaService bkbdetailService = new BkbDetailJpaServiceImpl();
	private BkkHeaderJpaService bkkheaderService = new BkkHeaderJpaServiceImpl();
	private BkkDetailJpaService bkkdetailService = new BkkDetailJpaServiceImpl();
	private BBankHeaderJpaService bbankheaderService = new BBankHeaderJpaServiceImpl();
	private BBankDetailJpaService bbankdetailService = new BBankDetailJpaServiceImpl();
	
	public TestModel(){
		testBbank();
		testBkb();
		testBkk();
	}

	public void testBkb(){
		//header
		Bkbheader header = new Bkbheader();
		BkbheaderPK headerPK = new BkbheaderPK();
		headerPK.setRefno("001");
//		headerPK.setDivision("Div1");
		
		Division div = new Division();
		div.setId("DIV1");
		div.setDivisi("NESTLE KEDIRI");
		
		header.setDivisionBean(div);
		header.setId(headerPK);
		
		bkbheaderService.updateObject(header);
		//detail
		Bkbdetail detail =new Bkbdetail();
		BkbdetailPK detailPK = new BkbdetailPK();
		detailPK.setRefno("001");
		detailPK.setNourut(1);

		detail.setId(detailPK);
		detail.setDivisionBean(div);
		detail.setDebetkredit("D");
		//customer
		Customer cust = new Customer();
		CustomerPK custPK = new CustomerPK();
		custPK.setCustno("K001");
		cust.setId(custPK);
		cust.setDivisionBean(div);
		//account
		Account account = new Account();
		account.setId("001");
		account.setName("Buku Besar");
		
		detail.setAccountBean(account);
		detail.setCustomerBean(cust);
		
		bkbdetailService.updateObject(detail);
		
		System.out.println("BKB SELESAI NO PROBLEM");
		
	}
	public void testBkk(){
		//header
		Bkkheader header = new Bkkheader();
		BkkheaderPK headerPK = new BkkheaderPK();
		headerPK.setRefno("001");
//		headerPK.setDivision("Div1");
		
		Division div = new Division();
		div.setId("DIV1");
		div.setDivisi("NESTLE KEDIRI");
		
		header.setDivisionBean(div);
		header.setId(headerPK);
		
		bkkheaderService.updateObject(header);
		//detail
		Bkkdetail detail =new Bkkdetail();
		BkkdetailPK detailPK = new BkkdetailPK();
		detailPK.setRefno("001");
		detailPK.setNourut(1);

		detail.setId(detailPK);
		detail.setDivisionBean(div);
		detail.setDebetkredit("D");
		//customer
		Customer cust = new Customer();
		CustomerPK custPK = new CustomerPK();
		custPK.setCustno("K001");
		cust.setId(custPK);
		cust.setDivisionBean(div);
		//account
		Account account = new Account();
		account.setId("001");
		account.setName("Buku Besar");
		
		detail.setAccountBean(account);
		detail.setCustomerBean(cust);
		
		bkkdetailService.updateObject(detail);
		
		System.out.println("BKB SELESAI NO PROBLEM");
		
	}
	public void testBbank(){
		//header
		Bbankheader header = new Bbankheader();
		BbankheaderPK headerPK = new BbankheaderPK();
		headerPK.setRefno("001");
//		headerPK.setDivision("Div1");
		
		Division div = new Division();
		div.setId("DIV1");
		div.setDivisi("NESTLE KEDIRI");
		
		header.setDivisionBean(div);
		header.setId(headerPK);
		
		bbankheaderService.updateObject(header);
		
		//detail
		Bbankdetail detail =new Bbankdetail();
		BbankdetailPK detailPK = new BbankdetailPK();
		detailPK.setRefno("001");
		detailPK.setNourut(1);

		detail.setId(detailPK);
		detail.setDivisionBean(div);
		detail.setDebetkredit("D");
		//customer
		Customer cust = new Customer();
		CustomerPK custPK = new CustomerPK();
		custPK.setCustno("K001");
		cust.setId(custPK);
		cust.setDivisionBean(div);
		//account
		Account account = new Account();
		account.setId("001");
		account.setName("Buku Besar");
		
		detail.setAccountBean(account);
		detail.setCustomerBean(cust);
		
		bbankdetailService.updateObject(detail);
		
		System.out.println("BKB SELESAI NO PROBLEM");
		
	}
	
	public static void main(String [] args){
		TestModel test = new TestModel();
		
	}
	
}
