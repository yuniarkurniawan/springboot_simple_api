package com.sekolah.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sekolah.demo.dto.MahasiswaDTO;
import com.sekolah.demo.dto.MahasiswaListDTO;
import com.sekolah.demo.entity.Fakultas;
import com.sekolah.demo.entity.Mahasiswa;
import com.sekolah.demo.repository.MahasiswaRepository;
import com.sekolah.demo.service.FakultasService;
import com.sekolah.demo.service.MahasiswaService;

@SpringBootTest
public class MahasiswaControllerTest {
	
	@Autowired
	MahasiswaService mahasiswaService;
	
	@Autowired
	FakultasService fakultasService;
	
	@Autowired
	MahasiswaRepository mahasiswaRepository;
	
	public void createData() {
		Fakultas fakultas = new Fakultas();
		fakultas.setIdFakultas(1L);
		fakultas.setNamaFakultas("FMIPA");
		fakultas.setKeteranganFakultas("Fakultas Matematika Dan Ilmu Pengetahuan Alam");
		fakultas = fakultasService.addFakultas(fakultas);
		
		Mahasiswa mahasiswa = new Mahasiswa();
		mahasiswa.setIdMahasiswa(1L);
		mahasiswa.setNamaMahasiswa("Testing Nama Mahasiswa");
		mahasiswa.setAlamatMahasiswa("Testing Alamat Mahasiswa");
		mahasiswa.setFakultas(fakultas);
		mahasiswaRepository.save(mahasiswa);
	}
	
	@Test
	public void testCreateMahasiswa() {
		this.createData();
		assertNotNull(mahasiswaService.getMahasiswaById(1L));
	}
	
	@Test
	public void testListMahasiswa() {
		this.createData();
		int page = 0;
		int size = 5;
		
		Pageable paging = PageRequest.of(page, size);
		Page<MahasiswaListDTO> pageMahasiswa = null;
		pageMahasiswa = mahasiswaService.getAllMahasiswaFakultasPageable(paging);
		assertThat(pageMahasiswa.getTotalElements()).isGreaterThan(0);
	}
	
	@Test
	public void testUpdateMahasiswa() {
		this.createData();
		
		MahasiswaDTO mahasiswaDTO = new MahasiswaDTO();
		mahasiswaDTO.setNama_mahasiswa("DATA BARU");
		mahasiswaDTO.setAlamat_mahasiswa("ALAMAT BARU");
		mahasiswaDTO.setFakultas(1L);
		
		Mahasiswa mahasiswaOld = mahasiswaService.updateMahasiswa(1L, mahasiswaDTO);
		assertEquals(mahasiswaOld.getNamaMahasiswa(), "DATA BARU");
	}
		
}
