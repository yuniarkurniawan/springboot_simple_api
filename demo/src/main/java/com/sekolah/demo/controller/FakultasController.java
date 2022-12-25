package com.sekolah.demo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sekolah.demo.entity.Fakultas;
import com.sekolah.demo.service.FakultasService;

@RestController
@RequestMapping("/api/v1/fakultas")
public class FakultasController {
	
	@Autowired
	private FakultasService fakultasService;
	
//	@GetMapping("/criteria_list")
//	public ResponseEntity<Map<String, Object>> getAllFakultasCriteria(
//			@RequestParam(required = false) String search,
//			@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "5") int size
//			){
//		Map<String, Object> jsonRespone = new LinkedHashMap<>();
//		try {
//			
//			List<Fakultas> dataCriteria = fakultasService.getAllFakultasCriteria(page, size, search.toLowerCase());
//			
//			jsonRespone.put("data", dataCriteria);
//			jsonRespone.put("current_page", page);
//			jsonRespone.put("total_items", dataCriteria.size());
//			jsonRespone.put("total_pages", dataCriteria.size()/size);
//			jsonRespone.put("message", "success");
//			jsonRespone.put("code", HttpStatus.OK);
//			
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		return new ResponseEntity<Map<String, Object>>(jsonRespone, HttpStatus.OK);
//	}
	
	@GetMapping("/criteria_list")
	public ResponseEntity<Map<String, Object>> getAllFakultasCriteria(
			@RequestParam(required = false) String search,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size
			){
		Map<String, Object> jsonRespone = new LinkedHashMap<>();
		try {
			
			jsonRespone = fakultasService.getAllFakultasCriteriaMap(page, size, search.toLowerCase());
			jsonRespone.put("message", "success");
			jsonRespone.put("code", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(jsonRespone, HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getAllFakultas(
			@RequestParam(required = false) String search,
			@RequestParam(defaultValue = "0") int page,
		    @RequestParam(defaultValue = "5") int size
			){
		
		Map<String, Object> jsonRespone = new LinkedHashMap<>();
		List<Fakultas> listFakultas = new ArrayList<>();
		Pageable paging = PageRequest.of(page, size);
		Page<Fakultas> pageFakultas = null;
		try {
			
			if(search==null) {
				pageFakultas = fakultasService.getAllFakultasPageable(paging);
			}else {
				pageFakultas = fakultasService.getAllFakultasFilterPageable(search.toLowerCase(), search.toLowerCase(), paging);
			}
			
			listFakultas = pageFakultas.getContent();
			jsonRespone.put("data", listFakultas);
			jsonRespone.put("current_page", pageFakultas.getNumber());
			jsonRespone.put("total_items", pageFakultas.getTotalElements());
			jsonRespone.put("total_pages", pageFakultas.getTotalPages());
			jsonRespone.put("message", "success");
			jsonRespone.put("code", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(jsonRespone, HttpStatus.OK);
	}
	
	@GetMapping("/get_by_id/{id}")
	public ResponseEntity<Fakultas> getFakultasById(@PathVariable("id") Long id){
		Fakultas fakultas = null;
		try {
			fakultas = fakultasService.getFakultasById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Fakultas>(fakultas, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Fakultas> addFakultas(@RequestBody Fakultas fakultasJsonBody){
		Fakultas fakultas = null;
		try {
			fakultas = fakultasService.addFakultas(fakultasJsonBody);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Fakultas>(fakultas, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Fakultas> updateFakultas(@PathVariable("id") Long id, @RequestBody Fakultas fakultasJsonBody){
		Fakultas fakultas = null;
		try {
			fakultas = fakultasService.updateFakultas(id, fakultasJsonBody);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<Fakultas>(fakultas, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Fakultas> deleteById(@PathVariable("id") Long id){
		Fakultas fakultas = null;
		try {
			fakultas = fakultasService.deleteFakultas(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Fakultas>(fakultas, HttpStatus.OK);
	}
}
