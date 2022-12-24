package com.sekolah.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sekolah.demo.dto.MahasiswaDTO;
import com.sekolah.demo.dto.MahasiswaListDTO;
import com.sekolah.demo.entity.Fakultas;
import com.sekolah.demo.entity.Mahasiswa;
import com.sekolah.demo.repository.FakultasRepository;
import com.sekolah.demo.repository.MahasiswaRepository;
import com.sekolah.demo.service.MahasiswaService;

@Service
public class MahasiswaServiceImpl implements MahasiswaService {

	@Autowired
	MahasiswaRepository mahasiswaRepository;
	
	@Autowired
	FakultasRepository fakultasRepository;
	
	@Override
	public Page<Mahasiswa> getAllMahasiswaPageable(Pageable pageable){
		return (Page<Mahasiswa>) mahasiswaRepository.findAll(pageable);
	}
			
	@Override
	public Page<MahasiswaListDTO> getAllMahasiswaFakultasFilterPageable(String search, Pageable pageable){
		return (Page<MahasiswaListDTO>) mahasiswaRepository.findMahasiswaFakultasFilterPageable(search, pageable);
	}
	
	@Override
	public Page<MahasiswaListDTO> getAllMahasiswaFakultasPageable(Pageable pageable){
		return (Page<MahasiswaListDTO>) mahasiswaRepository.findMahasiswaFakultasPageable(pageable);
	}
	
	@Override
	public List<Mahasiswa> getAllMahasiswa() {
		return (List<Mahasiswa>)mahasiswaRepository.findAll();
	}

	@Override
	public Mahasiswa getMahasiswaById(Long idMahasiswa) {
		Mahasiswa mahasiswa = null;
		try {
			mahasiswa = mahasiswaRepository.findById(idMahasiswa).orElse(null);
		} catch (Exception e) {
			e.getMessage();
		}
		
		return mahasiswa;
	}
	
	@Override
	public Mahasiswa addMahasiswa(MahasiswaDTO mahasiswaDTO) {
		Mahasiswa mahasiswa = null;
		try {
			Fakultas dataFakultas = fakultasRepository.findById(mahasiswaDTO.getFakultas()).orElse(null);
			Mahasiswa dataMahasiswa = new Mahasiswa();
			dataMahasiswa.setNamaMahasiswa(mahasiswaDTO.getNama_mahasiswa());
			dataMahasiswa.setAlamatMahasiswa(mahasiswaDTO.getAlamat_mahasiswa());
			dataMahasiswa.setFakultas(dataFakultas);
			mahasiswa = mahasiswaRepository.save(dataMahasiswa);
		} catch (Exception e) {
			e.getMessage();
		}
		return mahasiswa;
	}
	
	@Override
	public Mahasiswa updateMahasiswa(Long idMahasiswa, Mahasiswa mahasiswa) {
		Mahasiswa dataMahasiswa = mahasiswaRepository.findById(idMahasiswa).orElse(null);
		dataMahasiswa.setNamaMahasiswa(mahasiswa.getNamaMahasiswa());
		dataMahasiswa.setAlamatMahasiswa(mahasiswa.getAlamatMahasiswa());
		Fakultas fakultas = fakultasRepository.findById(mahasiswa.getFakultas().getIdFakultas()).orElse(null);
		dataMahasiswa.setFakultas(fakultas);
		return mahasiswaRepository.save(dataMahasiswa);
	}

	@Override
	public Mahasiswa deleteMahasiswa(Long idMahasiswa) throws Exception {
		Mahasiswa mahasiswa = null;
		try {
			mahasiswa = mahasiswaRepository.findById(idMahasiswa).orElse(null);
			if(mahasiswa==null) {
				throw new Exception("Data mahasiswa tidak ditemukan");
			}else {
				mahasiswaRepository.delete(mahasiswa);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return mahasiswa;
	}

}
