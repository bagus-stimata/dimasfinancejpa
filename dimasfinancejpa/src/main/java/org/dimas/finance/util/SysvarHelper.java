package org.dimas.finance.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Sysvar;
import org.dimas.finance.model.SysvarPK;

import com.ibm.icu.text.DateFormat;

public class SysvarHelper {
	private SysvarJpaService sysvarService =  new  SysvarJpaServiceImpl();
	
	private DivisionJpaService divisionService = new DivisionJpaServiceImpl();
	
	//1. CEK PARAMETER SYSTEM KALAU TIDAK ADA BUAT DEFAULT
	
	public void defaultConfigSysvar(){
		
		for (Division division: divisionService.findAll()){
			cekOrCreateNewConfigSysvar(division.getId());
		}
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
	 * 		_AR_OVERDUE = Overdue Default Nota Kredit
	 * 		_TOLE_OVERDUE = Toleransi Overdue Nota Kredit sebelum jadi OD
	 * 
	 * 		_URUT_BKB  = Nomor urut Buku Kas Besar
	 * 		_URUT_BKK  = Nomor urut Buku Kas Kecil
	 * 		_URUT_BBANK  = Nomor Urut Buku Bank
	 * 
	 */
	
	public void cekOrCreateNewConfigSysvar(String division){
		Sysvar sysvar = new Sysvar();
		sysvar = sysvarService.findById("_VERSION_PRG", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_VERSION_PRG");
			id.setDivision(division);
			sysvar.setId(id);

			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Program Version");
			sysvar.setGroupSysvar("_VERSION_PRG");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("1.0");
			
			sysvarService.createObject(sysvar);
		}
		
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_PRSH_KODE", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_PRSH_KODE");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Kode Perusahaan");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("101010");

			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_PRSH_NAMA", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_PRSH_NAMA");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Nama Perusahaan");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("PT. Forward Creative Tech");
			
			sysvarService.createObject(sysvar);
			
		}
		sysvar = sysvarService.findById("_PRSH_AREA", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_PRSH_AREA");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Area Perusahaan");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING2");
			sysvar.setNilaiString1("AREA");
			sysvar.setNilaiString1("AREA");
			
			sysvarService.createObject(sysvar);
			
		}
		sysvar = sysvarService.findById("_PRSH_REG", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_PRSH_REG");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Region Perusahaan");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING2");
			sysvar.setNilaiString1("REG");
			sysvar.setNilaiString2("REGION");

			sysvarService.createObject(sysvar);
			
		}
		
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_TRANSDATE", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_TRANSDATE");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Tanggal Transaksi Berjalan");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("DATE1");
			
			Date tgl = new Date(new Date().getYear(), 0, 1);			
			sysvar.setNilaiDate1(tgl);
			
			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_TR_PERIODE", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_TR_PERIODE");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Periode Transaksi Berjalan");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(1);
			
			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_TR_TAHUN", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_TR_TAHUN");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Tahun Transaksi Berjalan");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int currentYear = cal.get(Calendar.YEAR);
			sysvar.setNilaiInt1(currentYear);
			
			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_TR_PEKAN", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_TR_PEKAN");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Pekan Transaksi Berjalan");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(1);
			
			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_URUT_BKGR", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_URUT_BKGR");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Nomor Urut Buku Giro");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_URUT_TRANSF", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_URUT_TRANSF");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Nomor Urut Buku Transfer");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarService.createObject(sysvar);
			
		}
		
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_URUT_AR", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_URUT_AR");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Nomor Urut Dokumen Transaksi Account Receivable");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_TOLE_AR_EDIT", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_TOLE_AR_EDIT");
			id.setDivision(division);
			sysvar.setId(id);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Toleransi Boleh Edit Transaksi Account Receivable Seteleh Proses Akhir hari");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(7);
			
			sysvarService.createObject(sysvar);
			
		}
		
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_ALLOW_AR_MIN", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_ALLOW_AR_MIN");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Pembayaran AR Boleh Minus");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("BOL1");
			sysvar.setNilaiBol1(true);
			
			sysvarService.createObject(sysvar);
			
		}
		
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_AR_MIN_IS_LNS", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_AR_MIN_IS_LNS");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Jumlah Nominal AR Minus Yang Dianggap Lunas");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("DOUBLE1");
			sysvar.setNilaiDouble1(100.0);
			
			sysvarService.createObject(sysvar);
			
		}
		
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_ALLOW_AR_PLUS", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_ALLOW_AR_PLUS");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Pembayaran AR Boleh Surplus");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("BOL1");
			sysvar.setNilaiBol1(true);
			
			sysvarService.createObject(sysvar);
			
		}
		
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_AR_OVERDUE", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_AR_OVERDUE");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Overdue/Jatuh Tempo Default Nota Kredit");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(7);
			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_TOLE_OVERDUE", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_TOLE_OVERDUE");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Toleransi Overdue Nota Kredit sebelum jadi OD");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(7); //7 hari dari Jatuh tempo >> berarti 14 hari
			
			sysvarService.createObject(sysvar);
			
		}
		
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_URUT_BBANK", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_URUT_BBANK");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Nomor Urut Buku Bank");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_URUT_BKB", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_URUT_BKB");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Nomor Urut Buku Kas Besar");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarService.createObject(sysvar);
			
		}
		sysvar = new Sysvar();		
		sysvar = sysvarService.findById("_URUT_BKK", division);
		if (sysvar.getId()==null){
			SysvarPK id = new SysvarPK();
			id.setIdSysvar("_URUT_BKK");
			id.setDivision(division);
			sysvar.setId(id);
			
			Division div= new Division();
			div.setId(division);
			sysvar.setDivisionBean(div);
			
			sysvar.setDeskripsi("Nomor Urut Buku Kas Kecil");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
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
