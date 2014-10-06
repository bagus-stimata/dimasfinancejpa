package org.blitar.bawasda.simhp.test;

import java.util.ArrayList;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaServiceImpl;

public class TestDao {
	
	public static void main(String [] args){
	
		TabelTemuanKelompokJpaService temuanKelompokService = new TabelTemuanKelompokJpaServiceImpl();
		
		TabelTemuanKelompok temuanKelompok = new TabelTemuanKelompok();
		temuanKelompok.setId("KEL01");
		temuanKelompok.setDescription("Menemukan Penyimpangan A");
		
		temuanKelompokService.updateObject(temuanKelompok);
//		List<TabelPenyebabGrup> list = new ArrayList<TabelPenyebabGrup>();
//		list = tabelPenyebabJpaService.findAll();
//
//		for (TabelPenyebabGrup itemGrup: list){
//			
//		}
		
		System.out.println("Oke Selesai");
		
	}
	
}
