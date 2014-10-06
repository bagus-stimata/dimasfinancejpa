package org.blitar.bawasda.simhp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.blitar.bawasda.simhp.model.Sysvar;
import org.blitar.bawasda.simhp.model.User;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.UserJpaService;
import org.blitar.bawasda.simhp.service.UserJpaServiceImpl;

import com.ibm.icu.text.DateFormat;

public class UserHelper {
	private UserJpaService userService =  new  UserJpaServiceImpl();
	
	//1. CEK PARAMETER SYSTEM KALAU TIDAK ADA BUAT DEFAULT
	
	public void defaultConfigUser(){		
		cekOrCreateNewConfigUser();
		//1. INFORMASI PERUSAHAAN
		
		//2. TRANSAKSI BERJALAN
		//3. NOMOR URUT TRANSAKSI
	}

	/**		
	 * 	JUMLAH USER DEFAULT  = 16
	 * 
	 * 		_VERSION_PRG	= Program Version
	 * 
	 */
	
	public void cekOrCreateNewConfigUser(){
		User user = new User();
		user = userService.findById("ADMINISTRATOR");
		if (user.getUserId()==null){
			user.setUserId("ADMINISTRATOR");
			user.setUserPassword("1234");
			user.setActive(true);
			
			userService.createObject(user);
		}
		
		user = new User();		
		user = userService.findById("USER1");
		if (user.getUserId()==null){
			user.setUserId("USER1");
			user.setUserPassword("1234");
			user.setActive(true);
			
			userService.createObject(user);
			
		}
		
	}
	
	//2. TanggalTransaksi Berjalan
	public Date getTanggalTransaksiBerjalan(){
		Date transDate = null;
		return transDate;
	}
	
	public static void main(String [] args){
		UserHelper helper = new UserHelper();
		helper.defaultConfigUser();
		
	}
}
