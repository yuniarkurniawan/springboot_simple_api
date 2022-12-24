package com.sekolah.demo.dto;

public class MahasiswaDTO {
	private String nama_mahasiswa;
	private String alamat_mahasiswa;
	private Long fakultas;
	
	public MahasiswaDTO() {
		
	}

	public String getNama_mahasiswa() {
		return nama_mahasiswa;
	}

	public void setNama_mahasiswa(String nama_mahasiswa) {
		this.nama_mahasiswa = nama_mahasiswa;
	}

	public String getAlamat_mahasiswa() {
		return alamat_mahasiswa;
	}

	public void setAlamat_mahasiswa(String alamat_mahasiswa) {
		this.alamat_mahasiswa = alamat_mahasiswa;
	}

	public Long getFakultas() {
		return fakultas;
	}

	public void setFakultas(Long fakultas) {
		this.fakultas = fakultas;
	}
	
}
