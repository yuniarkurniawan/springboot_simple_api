package com.sekolah.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sekolah.demo.entity.Fakultas;

@Repository
public interface FakultasRepository extends JpaRepository<Fakultas, Long> {
	Page<Fakultas> findAll(Pageable pageable);
	Page<Fakultas> findByKeteranganFakultasContainingIgnoreCaseOrNamaFakultasIgnoreCase(String keterangan, String nama, Pageable pageable);
}
