package com.sekolah.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sekolah.demo.entity.Fakultas;
import com.sekolah.demo.repository.FakultasRepository;
import com.sekolah.demo.service.FakultasService;

@Service
public class FakultasServiceImpl implements FakultasService {

	@Autowired
	private FakultasRepository fakultasRepository;
	
	@Override
	public Page<Fakultas> getAllFakultasPageable(Pageable pageable){
		return (Page<Fakultas>) fakultasRepository.findAll(pageable);
	}
	
	@Override
	public Page<Fakultas> getAllFakultasFilterPageable(String keterangan, String nama, Pageable pageable){
		return (Page<Fakultas>) fakultasRepository.findByKeteranganFakultasContainingIgnoreCaseOrNamaFakultasIgnoreCase(keterangan, nama, pageable);
	}
	
	@Override
	public List<Fakultas> getAllFakultas() {
		return (List<Fakultas>)fakultasRepository.findAll();
	}

	@Override
	public Fakultas getFakultasById(Long fakultasId) {
		return fakultasRepository.findById(fakultasId).orElse(null);
	}

	@Override
	public Fakultas addFakultas(Fakultas fakultas) {
		return fakultasRepository.save(fakultas);
	}
	
	@Override
	public Fakultas updateFakultas(Long fakultasId, Fakultas fakultas) {
		Fakultas dataFakultas = fakultasRepository.findById(fakultasId).orElse(null);
		dataFakultas.setNamaFakultas(fakultas.getNamaFakultas());
		dataFakultas.setKeteranganFakultas(fakultas.getKeteranganFakultas());
		return fakultasRepository.save(dataFakultas);
	}

	@Override
	public Fakultas deleteFakultas(Long fakultasId) throws Exception {
		Fakultas deletedFakultas = null;
		try {
			deletedFakultas = fakultasRepository.findById(fakultasId).orElse(null);
			if(deletedFakultas == null) {
				throw new Exception("Data fakultas tidak ditemukan");
			}else {
				fakultasRepository.delete(deletedFakultas);
			}
		} catch (Exception e) {
			throw e;
		}
		return deletedFakultas;
	}

}
