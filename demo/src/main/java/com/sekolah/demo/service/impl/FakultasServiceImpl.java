package com.sekolah.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sekolah.demo.entity.Fakultas;
import com.sekolah.demo.repository.FakultasRepository;
import com.sekolah.demo.service.FakultasService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class FakultasServiceImpl implements FakultasService {

	@Autowired
	private FakultasRepository fakultasRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Fakultas> getAllFakultasCriteria(int page, int size, String search){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Fakultas> query = builder.createQuery(Fakultas.class);
		Root<Fakultas> root = query.from(Fakultas.class);
		
		Expression<String> pathNamaFakultas = root.get("namaFakultas");
		Expression<String> lowerNamaFakultas = builder.lower(pathNamaFakultas);
		
		Expression<String> pathKeteranganFakultas = root.get("keteranganFakultas");
		Expression<String> lowerKeteranganFakultas = builder.lower(pathKeteranganFakultas);
		
		Predicate predicateNamaFakultas = builder.like(lowerNamaFakultas, "%"+search+"%");
		Predicate predicateKeteranganFakultas = builder.like(lowerKeteranganFakultas, "%"+search+"%");
		
		query.where(builder.or(predicateNamaFakultas,predicateKeteranganFakultas));
		query.orderBy(builder.asc(root.get("namaFakultas")));
		
		List<Fakultas> result = entityManager
				.createQuery(query.select(root))
				.setMaxResults(size)
				.setFirstResult(page)
				.getResultList();
		
		return result;
	}
	
	
	@Override
	public Map<String, Object> getAllFakultasCriteriaMap(int page, int size, String search){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Fakultas> query = builder.createQuery(Fakultas.class);
		Root<Fakultas> root = query.from(Fakultas.class);
		
		Expression<String> pathNamaFakultas = root.get("namaFakultas");
		Expression<String> lowerNamaFakultas = builder.lower(pathNamaFakultas);
		
		Expression<String> pathKeteranganFakultas = root.get("keteranganFakultas");
		Expression<String> lowerKeteranganFakultas = builder.lower(pathKeteranganFakultas);
		
		Predicate predicateNamaFakultas = builder.like(lowerNamaFakultas, "%"+search+"%");
		Predicate predicateKeteranganFakultas = builder.like(lowerKeteranganFakultas, "%"+search+"%");
		
		query.where(builder.or(predicateNamaFakultas,predicateKeteranganFakultas));
		query.orderBy(builder.asc(root.get("namaFakultas")));
		
		List<Fakultas> result = entityManager
				.createQuery(query.select(root))
				.setMaxResults(size)
				.setFirstResult(page)
				.getResultList();
		
		List<Fakultas> total = entityManager
				.createQuery(query.select(root))
				.getResultList();
		
		Map<String, Object> data = new HashMap<>();
		data.put("data", result);
		data.put("current_page", page);
		data.put("total_items", total.size());
		data.put("total_pages", (total.size())/size);
				
		return data;
	}
	
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
