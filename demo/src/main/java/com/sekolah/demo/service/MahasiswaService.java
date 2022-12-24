package com.sekolah.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sekolah.demo.dto.MahasiswaDTO;
import com.sekolah.demo.dto.MahasiswaListDTO;
import com.sekolah.demo.entity.Mahasiswa;

public interface MahasiswaService {
	public List<Mahasiswa> getAllMahasiswa();
	public Page<Mahasiswa> getAllMahasiswaPageable(Pageable pageable);
	
	public Mahasiswa getMahasiswaById(Long idMahasiswa);
	public Mahasiswa addMahasiswa(MahasiswaDTO mahasiswaDTO);
	public Mahasiswa updateMahasiswa(Long idMahasiswa, MahasiswaDTO mahasiswaDTO);
	public Mahasiswa deleteMahasiswa(Long idMahasiswa) throws Exception;
	
	public Page<MahasiswaListDTO> getAllMahasiswaFakultasFilterPageable(String search, Pageable pageable);
	public Page<MahasiswaListDTO> getAllMahasiswaFakultasPageable(Pageable pageable);
}
