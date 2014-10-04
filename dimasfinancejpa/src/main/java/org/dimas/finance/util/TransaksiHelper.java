package org.dimas.finance.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dimas.finance.cashandbank.BukuKasKecilTab;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaService;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BBankHeaderJpaService;
import org.dimas.finance.jpa.dao.BBankHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkbHeaderJpaService;
import org.dimas.finance.jpa.dao.BkbHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkkHeaderJpaService;
import org.dimas.finance.jpa.dao.BkkHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuGiroJpaService;
import org.dimas.finance.jpa.dao.BukuGiroJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuTransferJpaService;
import org.dimas.finance.jpa.dao.BukuTransferJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.model.Arpaymentheader;
import org.dimas.finance.model.Bbankheader;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.Bkkheader;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Bukutransfer;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Sysvar;

public class TransaksiHelper {
	private SysvarJpaService sysvarService;
	private ArPaymentHeaderJpaService arpaymentHeaderService;
	private BukuGiroJpaService bukuGiroService;
	private BukuTransferJpaService bukuTransferService;
	
	private BkbHeaderJpaService bkbHeaderService;
	private BkkHeaderJpaService bkkHeaderService;
	private BBankHeaderJpaService bbankHeaderService;
	
	public TransaksiHelper(){
		sysvarService = new SysvarJpaServiceImpl();
		arpaymentHeaderService = new ArPaymentHeaderJpaServiceImpl();
		bukuGiroService = new BukuGiroJpaServiceImpl();
		bukuTransferService = new BukuTransferJpaServiceImpl();
		
		bkbHeaderService = new BkbHeaderJpaServiceImpl();
		bkkHeaderService = new BkkHeaderJpaServiceImpl();
		bbankHeaderService = new BBankHeaderJpaServiceImpl();
		
	}
	
	public String getCurrentNomorUrutArPayment(Division division){
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarService.findAllById("_URUT_AR", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = nomorUrutSekarang;
		return currentKode;
	}	
	public String getNewNomorUrutArPayment(Division division){
		boolean baru=true;
		
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarService.findAllById("_URUT_AR", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (arpaymentHeaderService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (arpaymentHeaderService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				Arpaymentheader domain = new Arpaymentheader();
				List<Arpaymentheader> listDomain = new ArrayList<Arpaymentheader>();
				listDomain = arpaymentHeaderService.findAllById(newKode, division.getId());
				
				if (listDomain.size()==0){
					kodeKembar = false;
				}
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		} else {
			
		}
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarService.updateObject(sysvar);
		
		return newKode;
	}
	
	public Date getCurrentTanggalTransaksiBerjalan(Division division){
		Date transDate = new Date();
		//1. AMBIL TANGGAL TRANSAKSI BERJALAN SEKARANG
		Sysvar sysvar = new Sysvar();
		sysvar = sysvarService.findById("_TRANSDATE", division.getId());
		 if (sysvar.getNilaiDate1()!=null){
			 transDate = sysvar.getNilaiDate1();
		 }
		return transDate;
	}
	public String getCurrentNomorUrutBukuGiro(Division division){
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarService.findAllById("_URUT_BKGR", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = nomorUrutSekarang;
		return currentKode;
	}
	public String getNewNomorUrutBukuGiro(Division division){
		boolean baru=true;
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarService.findAllById("_URUT_BKGR", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (bukuGiroService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (arpaymentHeaderService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				Bukugiro bukuGiro = new Bukugiro();
				List<Bukugiro> listBukugiro = new ArrayList<Bukugiro>();
				listBukugiro = bukuGiroService.findAllById(newKode, division.getId());
				
				if (listBukugiro.size()==0){
					kodeKembar = false;
				}
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarService.updateObject(sysvar);
		
		return newKode;
	}
	
	public String getCurrentNomorUrutBukuTransfer(Division division){
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarService.findAllById("_URUT_TRANSF", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = nomorUrutSekarang;
		return currentKode;
	}
	public String getNewNomorUrutBukuTransfer(Division division){
		boolean baru=true;
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarService.findAllById("_URUT_TRANSF", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (bukuTransferService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (arpaymentHeaderService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				Bukutransfer bukuTransfer = new Bukutransfer();
	//			bukuTransfer = bukuTransferService.findById(newKode);
				List<Bukutransfer> listBukutransfer = new ArrayList<Bukutransfer>();
				listBukutransfer = bukuTransferService.findAllById(newKode, division.getId());
				
				if (listBukutransfer.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarService.updateObject(sysvar);
		
		return newKode;
	}
	
	public String getCurrentNomorUrutBukuKasBesar(Division division){
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		//1. ** POINT GANTI (CUMA SATU)		
		listSysvar = sysvarService.findAllById("_URUT_BKB", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = nomorUrutSekarang;
		return currentKode;
	}
	public String getNewNomorUrutBukuKasBesar(Division division){
		boolean baru=true;
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		//1. ** POINT GANTI
		listSysvar = sysvarService.findAllById("_URUT_BKB", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		//2. ** POINT GANTI
		if (bkbHeaderService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (arpaymentHeaderService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//3. ** POINT GANTI (TIGA BUAH BARIS)
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				Bkbheader item = new Bkbheader();
	//			bukuTransfer = bukuTransferService.findById(newKode);
				List<Bkbheader> listItem = new ArrayList<Bkbheader>();
				listItem = bkbHeaderService.findAll(newKode, division.getId());
				
				if (listItem.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarService.updateObject(sysvar);
		
		return newKode;
	}

	public String getCurrentNomorUrutBukuKasKecil(Division division){
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		//1. ** POINT GANTI (CUMA SATU)		
		listSysvar = sysvarService.findAllById("_URUT_BKK", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = nomorUrutSekarang;
		return currentKode;
	}
	public String getNewNomorUrutBukuKasKecil(Division division){
		boolean baru=true;
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		//1. ** POINT GANTI
		listSysvar = sysvarService.findAllById("_URUT_BKK", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		//2. ** POINT GANTI
		if (bkkHeaderService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (arpaymentHeaderService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//3. ** POINT GANTI (TIGA BUAH BARIS)
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				Bkkheader item = new Bkkheader();
				List<Bkkheader> listItem = new ArrayList<Bkkheader>();
				listItem = bkkHeaderService.findAll(newKode, division.getId());
				
				if (listItem.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarService.updateObject(sysvar);
		
		return newKode;
	}
	

	public String getCurrentNomorUrutBukuBank(Division division){
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		//1. ** POINT GANTI (CUMA SATU)		
		listSysvar = sysvarService.findAllById("_URUT_BBANK", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = nomorUrutSekarang;
		return currentKode;
	}
	public String getNewNomorUrutBukuBank(Division division){
		boolean baru=true;
		String nomorUrutSekarang = "0000001";
		int panjang = 7;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		//1. ** POINT GANTI
		listSysvar = sysvarService.findAllById("_URUT_BBANK", division.getId());
		if (listSysvar.size() > 0){
			//AMBIL SYSVAR
			sysvar = listSysvar.get(0);
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			panjang = sysvar.getLenghtData();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		//2. ** POINT GANTI
		if (bbankHeaderService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (arpaymentHeaderService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//3. ** POINT GANTI (TIGA BUAH BARIS)
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				Bbankheader item = new Bbankheader();
				List<Bbankheader> listItem = new ArrayList<Bbankheader>();
				listItem = bbankHeaderService.findAll(newKode, division.getId());
				
				if (listItem.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarService.updateObject(sysvar);
		
		return newKode;
	}
	
	public static void main(String [] args){
		TransaksiHelper helper = new TransaksiHelper();
		Division div = new Division();
		div.setId("DIV2");
//		System.out.println(helper.getNewNomorUrutBukuGiro(div));
//		System.out.println(helper.getNewNomorUrutBukuTransfer(div));
		
		System.out.println(helper.getNewNomorUrutBukuKasBesar(div));
		System.out.println(helper.getNewNomorUrutBukuKasKecil(div));
		System.out.println(helper.getNewNomorUrutBukuBank(div));
		System.out.println(helper.getCurrentTanggalTransaksiBerjalan(div));
		System.out.println(helper.getNewNomorUrutArPayment(div));
		
		
	}
	
}
