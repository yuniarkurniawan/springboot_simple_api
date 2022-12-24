package com.sekolah.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="fakultas")
public class Fakultas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long idFakultas;
	
	@Column(name="nama_fakultas")
	private String namaFakultas;
	
	@Column(name="keterangan")
	private String keteranganFakultas;
	
//	@OneToMany(mappedBy = "order")
//    private List<Mahasiswa> items = new ArrayList<Mahasiswa>();
	
	public Fakultas() {
		super();
	}

	public Fakultas(Long idFakultas, String namaFakultas, String keteranganFakultas) {
		super();
		this.idFakultas = idFakultas;
		this.namaFakultas = namaFakultas;
		this.keteranganFakultas = keteranganFakultas;
	}

	public Long getIdFakultas() {
		return idFakultas;
	}

	public void setIdFakultas(Long idFakultas) {
		this.idFakultas = idFakultas;
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

	@Override
	public String toString() {
		return "Fakultas [idFakultas=" + idFakultas + ", namaFakultas=" + namaFakultas + ", keteranganFakultas="
				+ keteranganFakultas + "]";
	}
	
//	public Set<Mahasiswa> getMahasiswas() {
//		return mahasiswas;
//	}
//
//	public void setMahasiswas(Set<Mahasiswa> mahasiswas) {
//		this.mahasiswas = mahasiswas;
//	}

//	@Override
//	public String toString() {
//		return "Fakultas [idFakultas=" + idFakultas + ", namaFakultas=" + namaFakultas + ", keteranganFakultas="
//				+ keteranganFakultas + ", mahasiswas=" + mahasiswas + "]";
//	}
	
	
	
}
