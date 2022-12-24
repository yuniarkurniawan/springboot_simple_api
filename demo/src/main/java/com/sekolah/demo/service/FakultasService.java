package com.sekolah.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sekolah.demo.entity.Fakultas;

public interface FakultasService {
	public List<Fakultas> getAllFakultas();
	public Page<Fakultas> getAllFakultasPageable(Pageable pageable);
	public Page<Fakultas> getAllFakultasFilterPageable(String keterangan, String nama, Pageable pageable);
	
	public Fakultas getFakultasById(Long fakultasId);
	public Fakultas updateFakultas(Long fakultasId, Fakultas fakultas);
	public Fakultas addFakultas(Fakultas fakultas);
	public Fakultas deleteFakultas(Long fakultasId) throws Exception;
}
