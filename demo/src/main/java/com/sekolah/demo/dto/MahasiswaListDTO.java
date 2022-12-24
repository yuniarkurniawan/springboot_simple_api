package com.sekolah.demo.dto;

public class MahasiswaListDTO {
		
	private Long id;
	private String namaMahasiswa;
	private String alamatMahasiswa;
	private String namaFakultas;
	private String keteranganFakultas;
		
	public MahasiswaListDTO(Long id, String namaMahasiswa, String alamatMahasiswa, String namaFakultas,
			String keteranganFakultas) {
		super();
		this.id = id;
		this.namaMahasiswa = namaMahasiswa;
		this.alamatMahasiswa = alamatMahasiswa;
		this.namaFakultas = namaFakultas;
		this.keteranganFakultas = keteranganFakultas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNamaMahasiswa() {
		return namaMahasiswa;
	}
	public void setNamaMahasiswa(String namaMahasiswa) {
		this.namaMahasiswa = namaMahasiswa;
	}
	public String getAlamatMahasiswa() {
		return alamatMahasiswa;
	}
	public void setAlamatMahasiswa(String alamatMahasiswa) {
		this.alamatMahasiswa = alamatMahasiswa;
	}
	public String getNamaFakultas() {
		return namaFakultas;
	}
	public void setNamaFakultas(String namaFakultas) {
		this.namaFakultas = namaFakultas;
	}
	public String getKeteranganFakultas() {
		return keteranganFakultas;
	}
	public void setKeteranganFakultas(String keteranganFakultas) {
		this.keteranganFakultas = keteranganFakultas;
	}	
}
