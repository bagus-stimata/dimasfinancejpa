package org.blitar.bawasda.simhp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.blitar.bawasda.simhp.model.Sysvar;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;

import com.ibm.icu.text.DateFormat;

public class SysvarHelper {
	private SysvarJpaService sysvarService =  new  SysvarJpaServiceImpl();
	
	//1. CEK PARAMETER SYSTEM KALAU TIDAK ADA BUAT DEFAULT
	
	public void defaultConfigSysvar(){		
		cekOrCreateNewConfigSysvar();
		//1. INFORMASI PERUSAHAAN
		
		//2. TRANSAKSI BERJALAN
		//3. NOMOR URUT TRANSAKSI
	}

	/**		
	 * 	JUMLAH PARAMETER SYSTEM = 16
	 * 
	 * 		_VERSION_PRG	= Program Version
	 * 		_PRSH_KODE		= Kode Perusahaan
	 * 		_PRSH_NAMA		= Nama Perusahaan
	 * 		_PRSH_AREA		= Area Perusahaan
	 * 		_PRSH_REG		= Region Perusahaan
	 * 
	 * 		_TRANSDATE 		= Tanggal Transaksi
	 * 		_TR_PEKAN		= Pekan Transaksi Berjalan
	 * 		_TR_PERIODE		= Periode/BULAN Transaksi Berjalan
	 * 		_TR_TAHUN		= Tahun Transaksi Transaksi Berjalan
	 * 		_URUT_BKGR		= Urut Buku Giro 
	 * 		_URUT_TRANSF	= Urut Buku Transfer
	 * 		_URUT_AR 		= Urut Account Receivable
	 * 		_TOLE_AR_EDIT   = Tolerance Ar Edit
	 * 		_ALLOW_AR_MIN	= AR Boleh Minus
	 * 		_AR_MIN_IS_LNS	= Jumlah Nominal AR Minus Yang Dianggap Lunas
	 * 		_ALLOW_AR_PLUS  = AR Boleh Plus
	 * 
	 * 		_URUT_BKB  = Nomor urut Buku Kas Besar
	 * 		_URUT_BKK  = Nomor urut Buku Kas Kecil
	 * 		_URUT_BBANK  = Nomor Urut Buku Bank
	 * 
	 */
	
	public void cekOrCreateNewConfigSysvar(){
		Sysvar sysvar = new Sysvar();
		sysvar = sysvarService.findById("_VERSION_PRG");
		if (sysvar.getIdSysvar()==null){
			sysvar.setIdSysvar("_VERSION_PRG");
			sysvar.setDeskripsi("Program Version");
			sysvar.setGroupSysvar("_VERSION_PRG");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("1.0");
			
			sysvarService.createObject(sysvar);
		}
		
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_PRSH_KODE");
		if (sysvar.getIdSysvar()==null){
			sysvar.setIdSysvar("_PRSH_KODE");
			
			sysvar.setDeskripsi("Kode Perusahaan/Instansi");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("101010");

			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_PRSH_NAMA");
		if (sysvar.getIdSysvar()==null){
			sysvar.setIdSysvar("_PRSH_NAMA");
			
			sysvar.setDeskripsi("Nama Perusahaan/Instansi");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("PT. Forward Creative Tech");
			
			sysvarService.createObject(sysvar);
			
		}
		
	}
	
	//2. TanggalTransaksi Berjalan
	public Date getTanggalTransaksiBerjalan(){
		Date transDate = null;
		return transDate;
	}
	
	public static void main(String [] args){
		SysvarHelper helper = new SysvarHelper();
		helper.defaultConfigSysvar();
		
	}
}
