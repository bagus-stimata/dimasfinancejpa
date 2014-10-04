package org.dimas.finance.ar;

import java.text.NumberFormat;

import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Arpaymentdetail;
import org.dimas.finance.model.ArpaymentdetailPK;
import org.dimas.finance.model.Arpaymentheader;
import org.dimas.finance.model.ArpaymentheaderPK;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Bukutransfer;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;

import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.Notification;

public class ArPaymentCustPembayaranPresenter implements ClickListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArPaymentCustPembayaranModel model;
	private ArPaymentCustPembayaranView view;
	
	public ArPaymentCustPembayaranPresenter(ArPaymentCustPembayaranModel model, 
			ArPaymentCustPembayaranView view){
		this.model = model;
		this.view = view;
		initData();
		initListener();		
		initDisplay();
		
	}
	
	public void initData(){		
		
	}
	
	public void setPenambahanPembayaran(){
		double penambahan =  (Double) view.getFieldSubTotalAmountPaid().getConvertedValue() - model.getAmountForThisDetail();
		model.setAmountPenambahan(penambahan);
		
//		System.out.println("a: " + (Double) view.getFieldSubTotalAmountPaid().getConvertedValue() + "\t B: " 
//		+ model.getAmountForThisDetail() + "\t C: " + penambahan);
		
	}
	
	public void setInvoiceTerbayar(){
		model.setAmountForThisDetail(model.getAmountPenambahan() + model.getAmountForThisDetail());
		
		view.getFieldInvoiceAmountPaid().setReadOnly(false);
		view.getFieldInvoiceAmountPaid().setValue(String.valueOf(model.getAmountPenambahan() + 
				(Double) view.getFieldInvoiceAmountPaid().getConvertedValue()));
		view.getFieldInvoiceAmountPaid().setReadOnly(true);
		
	}
	
	public void initListener(){
		//TOMBOL FORM PEMBAYARAN10
		//BUTTON EQUAL
		view.getBtnEqualCash().addClickListener(this);
		view.getBtnEqualGiro().addClickListener(this);
		view.getBtnEqualRetur().addClickListener(this);
		view.getBtnEqualTransfer().addClickListener(this);

		TextChangeListener listenerFieldCash = new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub				
				try{
					double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
					view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("CASH", nilaiAfter)));			
//					view.getFieldInvoiceAmountPaid().setValue(String.valueOf((Double) view.getFieldInvoiceAmountPaid().getConvertedValue() + penambahanBayar()));
					setPenambahanPembayaran();
					setInvoiceTerbayar();
				} catch (Exception ex){
					Notification.show("Karakter valid = [0-9]");
				}									
			}
		};
		
		TextChangeListener listenerFieldGiro = new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub				
				try{
					double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
					view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("GIRO", nilaiAfter)));						

					setPenambahanPembayaran();
					setInvoiceTerbayar();
					
				} catch (Exception ex){
					Notification.show("Karakter valid = [0-9]");
				}									
			}
		};
		
		TextChangeListener listenerFieldRetur = new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub				
				try{
					double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
					view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("RETUR", nilaiAfter)));	

					setPenambahanPembayaran();
					setInvoiceTerbayar();
					
				} catch (Exception ex){
					Notification.show("Karakter valid = [0-9]");
				}									
			}
		};
		TextChangeListener listenerFieldTransfer = new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub				
				try{
					double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
					view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("TRANSFER", nilaiAfter)));		

					setPenambahanPembayaran();
					setInvoiceTerbayar();
					
				} catch (Exception ex){
					Notification.show("Karakter valid = [0-9]");
				}									
			}
		};
		TextChangeListener listenerFieldPotLain = new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub				
				try{
					double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
					view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("POTLAIN", nilaiAfter)));		

					setPenambahanPembayaran();
					setInvoiceTerbayar();
					
				} catch (Exception ex){
					Notification.show("Karakter valid = [0-9]");
				}									
			}
		};
		
		view.getFieldCashPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldCashPay().addTextChangeListener(listenerFieldCash);
		view.getFieldGiroPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldGiroPay().addTextChangeListener(listenerFieldGiro);
		view.getFieldReturPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldReturPay().addTextChangeListener(listenerFieldRetur);
		view.getFieldTransferPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldTransferPay().addTextChangeListener(listenerFieldTransfer);
		view.getFieldPotLainPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldPotLainPay().addTextChangeListener(listenerFieldPotLain);
		
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnCancelForm().addClickListener(this);
		
		//COMBO LISTENER		
		ValueChangeListener comboGiroValueChangeListener = new ValueChangeListener() {
			
			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub
				Object itemId = event.getProperty().getValue();
				try{
					if (cekGiroAvailableAndPay(itemId) != 0 ) {
						//COBA REBIND
						view.getComboGiro().discard();
						view.getBinderArpaymentDetail().bind(view.getComboGiro(), "bukugiroBean");						
					}
					
				} catch(Exception ex){}
				
			}
		};

		ValueChangeListener comboTransferValueChangeListener = new ValueChangeListener() {
			
			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub
				Object itemId = event.getProperty().getValue();
				try{
					
					if (cekTransferAvailableAndPay(itemId) != 0 ){
						//COBA REBIND
						view.getComboTransfer().discard();
						view.getBinderArpaymentDetail().bind(view.getComboTransfer(), "bukutransferBean");
					}
				
				} catch(Exception ex){}
				
			}
		};
		
		ValueChangeListener comboReturValueChangeListener = new ValueChangeListener() {
			
			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub	
				Object itemId = event.getProperty().getValue();
				try{
					
					if (cekTransferAvailableAndPay(itemId) != 0 ){
						//COBA REBIND
						view.getComboRetur().discard();
						view.getBinderArpaymentDetail().bind(view.getComboRetur(), "returBean");
					}
				
				} catch(Exception ex){}
				
			}
		};
		
		//ALASAN DITARUH SETELAH TAMBAH LISTENER ADALAH SUPAYA PADA SAAT INIT TIDAK DIJALANKAN DULU
		view.getComboGiro().setImmediate(true);		
		view.getComboGiro().addValueChangeListener(comboGiroValueChangeListener);
		
		view.getComboTransfer().setImmediate(true);
		view.getComboTransfer().addValueChangeListener(comboTransferValueChangeListener);

		view.getComboRetur().setImmediate(true);
		view.getComboRetur().addValueChangeListener(comboReturValueChangeListener);
		
		
	}
	
	
	public void initDisplay(){		
		view.refreshData();
		//LEBIH BAIK DI KIRIM DARI PEMANGGIL
//		view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarNow("DUMMY", 0.0)));
	}
	
	public double penambahanBayar(){
		double totalBayarDetailNowLocal = totalBayarDetailNow("Dummy",0.0);
		double amount =0;
		double amountpaid =0;
		amount = (Double) view.getFieldInvoiceAmount().getConvertedValue();
		amountpaid = (Double) view.getFieldInvoiceAmountPaid().getConvertedValue();
		
//		double penambahan = amount - (totalBayarDetailNowLocal + amountpaid);
		double penambahan = amount - (amountpaid);
		
		return penambahan;
	}
	
	public double totalBayarDetailNow(String tipeBayar, double amountPay){
		double amountCash =0;
		double amountGiro =0;
		double amountRetur =0;
		double amountTransfer =0;
		double amountPotLain =0;
		
		if (tipeBayar.equals("CASH")){
			amountCash = amountPay;						
		} else {
			amountCash = (Double) view.getFieldCashPay().getConvertedValue();			
		}
		if (tipeBayar.equals("GIRO")){
			amountGiro = amountPay;
		} else {
			amountGiro = (Double) view.getFieldGiroPay().getConvertedValue();			
		}
		if (tipeBayar.equals("RETUR")){
			amountRetur = amountPay;
		} else {
			amountRetur = (Double) view.getFieldReturPay().getConvertedValue();
		}
		if (tipeBayar.equals("TRANSFER")){
			amountTransfer = amountPay;
		}else {
			amountTransfer = (Double) view.getFieldTransferPay().getConvertedValue();
		}
		if (tipeBayar.equals("POTLAIN")){
			amountPotLain = amountPay;
		}else {
			amountPotLain = (Double) view.getFieldPotLainPay().getConvertedValue();
		}
		
		double sumJumlahBayar = amountCash + amountGiro + amountRetur + amountTransfer + amountPotLain;
		
//		Notification.show("CASH: " + amountCash + "\t GIRO: " + amountGiro + "\t RETUR: " + amountRetur + "\t TRANSFER: " + amountTransfer);
		
		return sumJumlahBayar;
	}
	
	public void comboGiroChangeListener(){
		
	}
	public void comboTransferChangeListener(){		
	}
	
	public int cekGiroAvailableAndPay(Object itemId){
		int error =0;
		
		try {
			Bukugiro cekBukuGiro=new Bukugiro();
			
			cekBukuGiro = model.getBeanItemContainerBukuGiro().getItem(itemId).getBean();
			double amountAvailable = cekBukuGiro.getAmount() - cekBukuGiro.getAmountused();
			double amountGiroPayBefore = model.getArPaymentDetail().getGiroamountpay();
			double amountGiroPayAfter = (Double) view.getFieldGiroPay().getConvertedValue();
			
			double amountGiroPayPenambahan = amountGiroPayAfter - amountGiroPayBefore;
			
//##			String identitas = cekBukuGiro.getRefno() + " - " + cekBukuGiro.getGironumber() 
//					+ " - " + cekBukuGiro.getGironame();
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(0);
			nf.setMinimumFractionDigits(0);
			
			if (amountAvailable < amountGiroPayPenambahan){
				error = -1;
//##				Notification.show("Sisa plafon GIRO " + identitas + " = Rp. " + nf.format(amountAvailable));					
			}
			
		} catch (Exception ex){
				//GIRO BERARTI GAK PAKE
		}
		return error;
	}
	
	public int cekTransferAvailableAndPay(Object itemId){
		int error =0;
		
		try{
			Bukutransfer cekBukuTransfer = new Bukutransfer();
			
			cekBukuTransfer = model.getBeanItemContainerBukuTransfer().getItem(itemId).getBean();
//###			String identitas = cekBukuTransfer.getRefno() + " - " + cekBukuTransfer.getNasabah();
			double amountAvailable = cekBukuTransfer.getAmount() - cekBukuTransfer.getAmountused();
			double amountTransferPayBefore = model.getArPaymentDetail().getTransferamountpay();
			double amountTransferPayAfter = (Double) view.getFieldTransferPay().getConvertedValue();
			
			double amountTransferPayPenambahan = amountTransferPayAfter - amountTransferPayBefore;
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(0);
			nf.setMinimumFractionDigits(0);
			
			if (amountAvailable < amountTransferPayPenambahan){
				error = -1;
//###				Notification.show("Sisa plafon TRANSFER " + identitas + " = Rp. " + nf.format(amountAvailable));
			}
		} catch(Exception ex){
			//BERARTI GAK PAKE
		}
		
		return error;
	}	
	
	public int cekReturAvailableAndPay(Object itemId){
		int error =0;
		
		try{
			Arinvoice cekRetur = new Arinvoice();
			
			cekRetur = model.getBeanitemContainerReturBelumLunas().getItem(itemId).getBean();
			String identitas = cekRetur.getId().getInvoiceno() + " - " + cekRetur.getId().getDivision() + " - " + cekRetur.getCustomerBean().getCustname();
			double amountAvailable = cekRetur.getAmount() - cekRetur.getAmountpay();
			double amountReturPayBefore = model.getArPaymentDetail().getReturamountpay();
			double amountReturPayAfter = (Double) view.getFieldReturPay().getConvertedValue();
			
			double amountReturPayPenambahan = amountReturPayAfter - amountReturPayBefore;
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(0);
			nf.setMinimumFractionDigits(0);
			
			if (amountAvailable < amountReturPayPenambahan){
				error = -1;
				Notification.show("Sisa plafon RETUR " + identitas + " = Rp. " + nf.format(amountAvailable));
			}
		} catch(Exception ex){
			//BERARTI GAK PAKE
		}
		
		return error;
	}	
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnEqualCash()){
			//NEW VALUE >> penambahan + nilai sekarang			
			view.getFieldCashPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldCashPay().getConvertedValue()));	
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
			
		} else if (event.getButton()==view.getBtnEqualGiro()){
			//NEW VALUE >> penambahan + nilai sekaran			
			view.getFieldGiroPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldGiroPay().getConvertedValue()));			
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
			
		} else if (event.getButton()==view.getBtnEqualRetur()){
			//NEW VALUE >> penambahan + nilai sekaran			
			view.getFieldReturPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldReturPay().getConvertedValue()));
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));			
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
			
		} else if (event.getButton()==view.getBtnEqualTransfer()){		
			//NEW VALUE >> penambahan + nilai sekaran			
			view.getFieldTransferPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldTransferPay().getConvertedValue()));
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
			
		} else if (event.getButton()==view.getBtnEqualPotLain()){		
			
			//NEW VALUE >> penambahan + nilai sekaran			
			view.getFieldPotLainPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldPotLainPay().getConvertedValue()));
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
			
		} else if (event.getButton()==view.getBtnSaveForm()){
			try {
				
				boolean fieldGiroTransferReturKosong = false;
				boolean isValid = true;
				
				//GAK BOLEH CLOSE DULU
				model.setAllowCloseWindow(false);
				
				//CEK APAKAH JUMLAH BAYAR LEBIH BESAR DARI NILAI INVOICE
				double nilaiInvoice = (Double) view.getFieldInvoiceAmount().getConvertedValue();
				double jumlahBayar = (Double) view.getFieldInvoiceAmountPaid().getConvertedValue();
				
				//VALIDASI GIRO, TRANSFER DAN RETUR >> JIKA LEBIH BESAR DARI NOL MAKA HARUS ADA ISINYA
				if ((Double) view.getFieldGiroPay().getConvertedValue() > 0 && view.getComboGiro().getValue()==null){
					isValid = false;
					Notification.show("NOMOR GIRO TIDAK BOLEH KOSONG");
				}
				if ((Double) view.getFieldTransferPay().getConvertedValue() > 0 && view.getComboTransfer().getValue()==null){
					isValid = false;
					Notification.show("NOMOR TRANSFER TIDAK BOLEH KOSONG");
				}
				if ((Double) view.getFieldReturPay().getConvertedValue() > 0 && view.getComboRetur().getValue()==null){
					isValid = false;
					Notification.show("NOMOR RETUR TIDAK BOLEH KOSONG");
				}
				//KEBALIKAN DIATAS >> JIKA GIROPAY, TRANSFERPAY, RETURPAY ADALAH 0 maka tidak BOLEH ADA field BERISI	
				if ((Double) view.getFieldGiroPay().getConvertedValue() == 0 && view.getComboGiro().getValue()!=null){
					isValid = false;
					Notification.show("NOMINAL GIRO Rp. 0, NOMOR GIRO TIDAK TERPAKAI");
				}
				if ((Double) view.getFieldTransferPay().getConvertedValue() == 0 && view.getComboTransfer().getValue()!=null){
					isValid = false;
					Notification.show("NOMINAL TRANSFER Rp. 0, NOMOR TRANSFER TIDAK TERPAKAI");
				}
				if ((Double) view.getFieldReturPay().getConvertedValue() == 0 && view.getComboRetur().getValue()!=null){
					isValid = false;
					Notification.show("NOMINAL RETUR Rp. 0, NOMOR RETUR TIDAK TERPAKAI");
				}
				
				//VALIDASI NOMINAL  GIRO, TRANSFER DAN RETUR DENGAN AVAILABLE AMOUNT
				try{
					Object itemIdGiro = view.getComboGiro().getValue();
					if (cekGiroAvailableAndPay(itemIdGiro)!=0){
						isValid = false;
					}
				} catch(Exception ex){
				}

				try{
					Object itemIdTransfer = view.getComboTransfer().getValue();
					if (cekTransferAvailableAndPay(itemIdTransfer)!=0){
						isValid = false;
					}
				} catch(Exception ex){
				}
				try{
					Object itemIdRetur = view.getComboRetur().getValue();
					if (cekReturAvailableAndPay(itemIdRetur)!=0){
						isValid = false;
					}
				} catch(Exception ex){
				}
				
				
				//VALIDASI JUMLAH PEMBAYARAN
				if (nilaiInvoice < jumlahBayar) {
					isValid =false;
					Notification.show("PEMBAYARAN LEBIH BESAR DARI NILAI INVOICE!!..");
					
				}
				
				//CEK INPUT APA KOSONG SEMUA
				if (totalBayarDetailNow("Dummy",0.0)<= 0){
					isValid = false;
					Notification.show("TOTAL PEMBAYARAN TIDAK BOLEH 0");
				}
				//BUAT UPDATE TABEL GIRO, TRANSFER DAN RETUR DIBAWAH
				double amountGiroPayBefore = model.getArPaymentDetail().getGiroamountpay();
				double amountGiroPayAfter = (Double) view.getFieldGiroPay().getConvertedValue();				
				double amountGiroPayPenambahan = amountGiroPayAfter - amountGiroPayBefore;

				double amountTransferPayBefore = model.getArPaymentDetail().getTransferamountpay();
				double amountTransferPayAfter = (Double) view.getFieldTransferPay().getConvertedValue();				
				double amountTransferPayPenambahan = amountTransferPayAfter - amountTransferPayBefore;
				
				double amountReturPayBefore = model.getArPaymentDetail().getReturamountpay();
				double amountReturPayAfter = (Double) view.getFieldReturPay().getConvertedValue();				
				double amountReturPayPenambahan = amountReturPayAfter - amountReturPayBefore;
				
				//JIKA SELISIH MINUS BERARTI KEBANYAKAN BAYARNYA
				if ( isValid ==true){
					
					//OPERASI PENAMBAHAN  
					if (model.getFormOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){						
						String newNomorUrut="";
//						String newNomorUrut = model.getManagerTransaksi().getNewNomorUrutArPayment();
						//1. LENGKAPI DETAIL HEADER
//						model.getArPaymentHeader().setRefno(newNomorUrut);
						ArpaymentheaderPK id = new ArpaymentheaderPK();
						id.setRefno(newNomorUrut);
						
						model.getArPaymentHeader().setId(id);
						//2. LENGKAPI HEADER DETAIL
						ArpaymentdetailPK arpaymentdetailPK = new ArpaymentdetailPK();
						arpaymentdetailPK = model.getArPaymentDetail().getId();
						arpaymentdetailPK.setRefno(newNomorUrut);
						
						model.arPaymentDetail.setId(arpaymentdetailPK);
						
					}
					
					//UPDATE GIRO, TRANSFER, RETUR SEBELUM COMMIT >> MENGURANGI GIRO(pada database) DENGAN NILAI AWAL
					try{						
						//SEBELUM COMMIT
						Bukugiro newBukugiro = new Bukugiro();
//##						newBukugiro = model.getBukuGiroService().findById(model.getArPaymentDetail().getBukugiroBean().getRefno());
						newBukugiro.setAmountused(newBukugiro.getAmountused() - model.getArPaymentDetail().getGiroamountpay());
						model.getBukuGiroService().updateObject(newBukugiro);
						
					} catch(Exception ex){ 
					}										
					try{
						//SEBELUM COMMIT
						Bukutransfer newTransfer = new Bukutransfer();
//###						newTransfer = model.getBukuTransferService().findById(model.getArPaymentDetail().getBukutransferBean().getRefno());	
						newTransfer.setAmountused(newTransfer.getAmountused() - model.getArPaymentDetail().getTransferamountpay());
						model.getBukuTransferService().updateObject(newTransfer);
					} catch(Exception ex){
					}
					try{
						Arinvoice newRetur = new Arinvoice();
						newRetur = model.getArInvoiceService().findByPk(model.getArPaymentDetail().getReturBean().getId());	
						newRetur.setAmountpay(newRetur.getAmountpay() - model.getArPaymentDetail().getReturamountpay());
						model.getArInvoiceService().updateObject(newRetur);
					} catch(Exception ex){
					}
					
					//1. Commit Binder	
					view.getBinderArpaymentDetail().commit();
					//PERHITUNGAN LUNAS/TIDAK LUNAS ada pada >> CustomerCreditPresenter
					view.getBinderArinvoice().commit();					
					
					//2. Update Header(Jika New) dan Detail
					if (model.getFormOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())) {
						model.getArPaymentHeaderService().createObject(model.getArPaymentHeader());
						model.getArPaymentDetailService().createObject(model.getArPaymentDetail());
						
					}else {
						model.getArPaymentDetailService().updateObject(model.getArPaymentDetail());
					}
					
					
					//UPDATE GIRO, TRANSFER, RETUR SEBELUM COMMIT SESUDAH COMMIT  >> MENAMBAH AMOUNTPAY(pada database) DENGAN NILAI SEKARANG
					try{
						//SESUDAH COMMIT
						Bukugiro newBukugiro = new Bukugiro();
//##						newBukugiro = model.getBukuGiroService().findById(model.getArPaymentDetail().getBukugiroBean().getRefno());	
						newBukugiro.setAmountused(newBukugiro.getAmountused() + model.getArPaymentDetail().getGiroamountpay());
						model.getBukuGiroService().updateObject(newBukugiro);
					} catch(Exception ex){ 
					}
					try{
						Bukutransfer newTransfer = new Bukutransfer();
//###						newTransfer = model.getBukuTransferService().findById(model.getArPaymentDetail().getBukutransferBean().getRefno());	
						newTransfer.setAmountused(newTransfer.getAmountused() + model.getArPaymentDetail().getTransferamountpay());
						model.getBukuTransferService().updateObject(newTransfer);
					} catch(Exception ex){
					}
					
					try{
						Arinvoice newRetur = new Arinvoice();
						newRetur = model.getArInvoiceService().findByPk(model.getArPaymentDetail().getReturBean().getId());	
						newRetur.setAmountpay(newRetur.getAmountpay() + model.getArPaymentDetail().getReturamountpay());
						model.getArInvoiceService().updateObject(newRetur);
					} catch(Exception ex){
					}
					
					
					
					//3. Update ArInvoice
					model.getArInvoiceService().updateObject(model.getArInvoice());
					
					//UNTUK RELOAD INVOICE ADA DI FORM PEMANGGIL
					//BOLEH TUTUP JIKA CLOSE
					model.setAllowCloseWindow(true);
					
					Notification.show("Simpan Sukses..");
					
				} else {
					
//					System.out.println("ABC: " + jumlahBayar + "\t DEF: " 
//					+ nilaiInvoice);
					
				}
				
				
			} catch (CommitException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				Notification.show("Gagal Simpan!!");
			}
			
		} else if (event.getButton()==view.getBtnCancelForm()){
			
			view.getBinderArpaymentDetail().discard();			
			model.setAllowCloseWindow(true);
			
			
		}
		
	}
	
	
}
