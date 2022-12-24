package com.sekolah.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sekolah.demo.dto.MahasiswaListDTO;
import com.sekolah.demo.entity.Mahasiswa;

@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
	Page<Mahasiswa> findAll(Pageable pageable);
	
	@Query(value = "SELECT new com.sekolah.demo.dto.MahasiswaListDTO(m.id, m.namaMahasiswa, m.alamatMahasiswa, f.namaFakultas, f.keteranganFakultas)"
			+ " FROM Mahasiswa m JOIN Fakultas f ON m.fakultas.id = f.id"
			+ " WHERE LOWER(m.namaMahasiswa) LIKE %?1% OR LOWER(m.alamatMahasiswa) LIKE %?1%"
			+ " OR LOWER(f.namaFakultas) LIKE %?1% OR LOWER(f.keteranganFakultas) LIKE %?1%"
			+ " ORDER BY m.namaMahasiswa",
			countQuery = "SELECT COUNT(m.id)"
					+ " FROM Mahasiswa m JOIN Fakultas f ON m.fakultas.id = f.id"
					+ " WHERE LOWER(m.namaMahasiswa) LIKE %?1% OR LOWER(m.alamatMahasiswa) LIKE %?1%"
					+ " OR LOWER(f.namaFakultas) LIKE %?1% OR LOWER(f.keteranganFakultas) LIKE %?1%"
					+ " ORDER BY m.namaMahasiswa",
			nativeQuery = false)
	Page<MahasiswaListDTO> findMahasiswaFakultasFilterPageable(String search, Pageable pageable);
	
	@Query(value = "SELECT new com.sekolah.demo.dto.MahasiswaListDTO(m.id, m.namaMahasiswa, m.alamatMahasiswa, f.namaFakultas, f.keteranganFakultas)"
			+ " FROM Mahasiswa m JOIN Fakultas f ON m.fakultas.id = f.id"
			+ " ORDER BY m.namaMahasiswa",
			countQuery = "SELECT COUNT(m.id)"
					+ " FROM Mahasiswa m JOIN Fakultas f ON m.fakultas.id = f.id"
					+ " ORDER BY m.namaMahasiswa",
			nativeQuery = false)
	Page<MahasiswaListDTO> findMahasiswaFakultasPageable(Pageable pageable);
}
