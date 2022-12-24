package com.sekolah.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sekolah.demo.entity.Fakultas;
import com.sekolah.demo.service.FakultasService;

@SpringBootTest
public class FakultasControllerTest {
	@Autowired
	FakultasService fakultasService;
	
	public void createData() {
		Fakultas fakultas = new Fakultas();
		fakultas.setIdFakultas(1L);
		fakultas.setNamaFakultas("FMIPA");
		fakultas.setKeteranganFakultas("Fakultas Matematika Dan Ilmu Pengetahuan Alam");
		fakultasService.addFakultas(fakultas);
	}
	
	@Test
	public void testCreateFakultas() {
		this.createData();		
		assertNotNull(fakultasService.getFakultasById(1L));
	}
	
	@Test
	public void testListFakultas() {
		
		this.createData();
		int page = 0;
		int size = 5;
		
		Pageable paging = PageRequest.of(page, size);
		Page<Fakultas> pageFakultas = null;
		pageFakultas = fakultasService.getAllFakultasPageable(paging);
		assertThat(pageFakultas.getTotalElements()).isGreaterThan(0);
	}
	
	@Test
	public void testListFakultasSearch() {
		this.createData();
		
		int page = 1;
		int size = 5;
		String search = "FMIPA";
		
		Pageable paging = PageRequest.of(page, size);
		Page<Fakultas> pageFakultas = null;
		
		pageFakultas = fakultasService.getAllFakultasFilterPageable(search, search, paging);
		assertEquals(pageFakultas.getTotalElements(), 1);
	}
	
	@Test
	public void testListFakultasSearchEmptyData() {
		
		this.createData();
		int page = 1;
		int size = 5;
		String search = "fisip";
		
		Pageable paging = PageRequest.of(page, size);
		Page<Fakultas> pageFakultas = null;
		
		pageFakultas = fakultasService.getAllFakultasFilterPageable(search, search, paging);
		assertEquals(pageFakultas.getTotalElements(), 0);
	}
	
	@Test
	public void testUpdateFakultas() {
		this.createData();
		
		Fakultas fakultas = new Fakultas();
		fakultas.setNamaFakultas("FISIP");
		fakultas.setKeteranganFakultas("Fakultas Ilmu Sosial Dan Ilmu Politik");
		
		Fakultas fakultasOld = fakultasService.updateFakultas(1L, fakultas);
		assertEquals(fakultasOld.getNamaFakultas(), "FISIP");
	}
	
	
	@Test
	public void testDeleteFakultas() {
		this.createData();
		Fakultas fakultas = null;
		try {
			fakultas = fakultasService.deleteFakultas(1L);
		} catch (Exception e) {
			e.getMessage();
		}
		assertEquals(fakultas.getIdFakultas(), 1L);
	}
	
}
