package org.dimas.finance.systemsetting.harikerja;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.SysCalender;
import org.dimas.finance.model.SysCalenderPK;
import org.dimas.finance.model.Sysvar;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PeriodePresenter implements ClickListener{
	private PeriodeModel model;
	private PeriodeView view;
	
	public PeriodePresenter(PeriodeModel model, PeriodeView view){
		this.model = model;
		this.view = view;

		initListener();
		
	}
	
	public void initListener(){
		view.getBtnSetupCalender().addClickListener(this);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getButton()==view.getBtnSetupCalender()){
			 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Perubahan kelender kerja akan mempengaruhi seluruh sistem", "Yakin lakukan setup calender?", "Setup Calender", "Cancel",
		                new ConfirmDialog.Listener() {
		                    public void onClose(ConfirmDialog dialog) {
		                        if (dialog.isConfirmed()) {
		                            // Confirmed to continue
		                			startSetupCalender();
		                        	
		                        } else {
		                        	view.focustIdOrDesc();
		                        }
		                    }
		                });	        
			 commitDialog.setStyleName("dialog");
			 commitDialog.getOkButton().setStyleName("small");
			 commitDialog.getCancelButton().setStyleName("small");
			 //Jangan lupa
			 commitDialog.focus();
			
		}
		
		
		
	}
	
	public void startSetupCalender(){
		boolean isValid = true;
		
		String strDivision = null;
		Division divisionBean = new Division();		
		try{
			divisionBean = model.getBeanItemContainerDivision().getItem(view.getComboDivision().getValue()).getBean();
			strDivision = divisionBean.getId();
		} catch(Exception ex){}
		if (strDivision == null){
			isValid = false; 
		}
		
		//1. Ambil Parameter: divisi dan TAHUN SYSTEM
		int currentYear = 0;
		try {
			Sysvar sysvar = new Sysvar();
			List<Sysvar> listSysvar = new ArrayList<Sysvar>();
			listSysvar = model.getSysvarService().findAllById("_TR_TAHUN", strDivision);
			currentYear = listSysvar.get(0).getNilaiInt1();
		} catch(Exception ex){}
		if (currentYear<0){
			isValid = false;
		}
		
		//CEK TRANSAKSI DI PADA PERIODE BERJALAN
		//JIKA ADA MAKA TIDAK DIPERBOLEHKAN
		//1. Transaksi Cash bank, 
		//2. Transaksi AR, 
		//3. Transaksi GL
		
		
		Date tanggalAwal = view.getDateFieldAwalTanggal().getValue();
		if (tanggalAwal.getTime()<0){
			isValid = false;
		}
		
		if (isValid){
		
			Calendar calTanggal = Calendar.getInstance();
			calTanggal.setTime(tanggalAwal);
			
			//2. Bikin tanggal pada masing-masing periode-nya
			
			SysCalender item = new SysCalender();
			SysCalenderPK id = new SysCalenderPK();
			int fperiode = 1;
			int fweek = 1;
			int fday = 1;
			
			
			Integer weekPeriode1 = Integer.valueOf(view.getComboPeriode1().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 2
			Integer weekPeriode2 = Integer.valueOf(view.getComboPeriode2().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 3
			Integer weekPeriode3 = Integer.valueOf(view.getComboPeriode3().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 4
			Integer weekPeriode4 = Integer.valueOf(view.getComboPeriode4().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 5
			Integer weekPeriode5 = Integer.valueOf(view.getComboPeriode5().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 6
			Integer weekPeriode6 = Integer.valueOf(view.getComboPeriode6().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 7
			Integer weekPeriode7 = Integer.valueOf(view.getComboPeriode7().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 8
			Integer weekPeriode8 = Integer.valueOf(view.getComboPeriode8().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 9
			Integer weekPeriode9 = Integer.valueOf(view.getComboPeriode9().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 10
			Integer weekPeriode10 = Integer.valueOf(view.getComboPeriode10().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 11
			Integer weekPeriode11 = Integer.valueOf(view.getComboPeriode11().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 12
			Integer weekPeriode12 = Integer.valueOf(view.getComboPeriode12().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
			
			//Periode 13
			Integer weekPeriode13 = Integer.valueOf(view.getComboPeriode13().getConvertedValue().toString());
			for (int pekan=1; pekan<=weekPeriode1; pekan++){
				for (int hari=1; hari<=7; hari++){
					item = new SysCalender();
					id = new SysCalenderPK();
					id.setDivision(strDivision);
					id.setFdate(calTanggal.getTime());

					item.setFweek(fweek);
					item.setId(id);
					
					item.setFperiode(fperiode);
					
					boolean isWork = true;
					if (calTanggal.get(Calendar.DAY_OF_WEEK) ==1){
						isWork = false;
					}					
					item.setWorkdate(isWork);

					item.setFyear(currentYear);
					item.setFday(fday);
				
					item.setDivisionBean(divisionBean);
					
					//LALU NAIKKAN TANGGAL SATU HARI
					calTanggal.add(Calendar.DATE, 1);
					fday++;
					
//					System.out.println(item.getFperiode() + "\t" + item.getId().getFyear() + "\t" + item.getId().getFday() + "\t" + item.getFweek()+ item.getFdate());
					model.getSysCalenderService().updateObject(item);
					
				}
				fweek++;
			}
			fperiode++;
		
			Notification.show("Pemeliharaan Kalender Kerja Selesai Dilakukan. Cek Kalender kerja sekali lagi sebelum terdapat transaksi");
		}
	}
		
	
	

}
