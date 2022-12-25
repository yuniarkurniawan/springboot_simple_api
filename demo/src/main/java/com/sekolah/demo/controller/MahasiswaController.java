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

import com.sekolah.demo.dto.MahasiswaDTO;
import com.sekolah.demo.dto.MahasiswaListDTO;
import com.sekolah.demo.entity.Mahasiswa;
import com.sekolah.demo.service.FakultasService;
import com.sekolah.demo.service.MahasiswaService;

@RestController
@RequestMapping("/api/v1/mahasiswa")
public class MahasiswaController {
	
	@Autowired
	MahasiswaService mahasiswaService;
	
	@Autowired
	FakultasService fakultasService;
		
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getAllFakultas(
			@RequestParam(required=false) String search,
			@RequestParam(defaultValue = "0") int page,
		    @RequestParam(defaultValue = "5") int size
			){
		
		Map<String, Object> jsonRespone = new LinkedHashMap<>();
		List<MahasiswaListDTO> listMahasiswa = new ArrayList<>();
		Pageable paging = PageRequest.of(page, size);
		Page<MahasiswaListDTO> pageMahasiswa = null;
		try {
			
			if(search==null) {
				pageMahasiswa = mahasiswaService.getAllMahasiswaFakultasPageable(paging);
			}else {
				pageMahasiswa = mahasiswaService.getAllMahasiswaFakultasFilterPageable(search, paging);
			}
						
			listMahasiswa = pageMahasiswa.getContent();
			jsonRespone.put("data", listMahasiswa);
			jsonRespone.put("current_page", pageMahasiswa.getNumber());
			jsonRespone.put("total_items", pageMahasiswa.getTotalElements());
			jsonRespone.put("total_pages", pageMahasiswa.getTotalPages());
			jsonRespone.put("message", "success");
			jsonRespone.put("code", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(jsonRespone, HttpStatus.OK);
	}
	
	@GetMapping("/get_by_id/{id}")
	public ResponseEntity<Mahasiswa> getMahasiswaById(@PathVariable("id") Long id){
		Mahasiswa mahasiswa = null;
		try {
			mahasiswa = mahasiswaService.getMahasiswaById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Mahasiswa>(mahasiswa, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Mahasiswa> addMahasiswa(@RequestBody MahasiswaDTO mahasiswaDTO){
		
		Mahasiswa mahasiswa = null;
		try {
			mahasiswa = mahasiswaService.addMahasiswa(mahasiswaDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Mahasiswa>(mahasiswa, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Mahasiswa> updateMahasiswa(
			@PathVariable("id") Long id, 
			@RequestBody MahasiswaDTO mahasiswaDTO){
		Mahasiswa mahasiswa = null;
		try {
			mahasiswa = mahasiswaService.updateMahasiswa(id, mahasiswaDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<Mahasiswa>(mahasiswa, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Mahasiswa> deleteById(@PathVariable("id") Long id){
		Mahasiswa mahasiswa = null;
		try {
			mahasiswa = mahasiswaService.deleteMahasiswa(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Mahasiswa>(mahasiswa, HttpStatus.OK);
	}
}
