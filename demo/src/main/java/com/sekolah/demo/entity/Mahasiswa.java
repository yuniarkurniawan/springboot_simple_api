package com.sekolah.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="mahasiswa")
public class Mahasiswa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long idMahasiswa;
	
	@Column(name="nama_mahasiswa")
	private String namaMahasiswa;
	
	@Column(name="alamat_mahasiswa")
	private String alamatMahasiswa;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fakultas_id", referencedColumnName = "id")
	private Fakultas fakultas;
	
	public Mahasiswa(Long idMahasiswa, String namaMahasiswa, String alamatMahasiswa, Fakultas fakultas) {
		super();
		this.idMahasiswa = idMahasiswa;
		this.namaMahasiswa = namaMahasiswa;
		this.alamatMahasiswa = alamatMahasiswa;
		this.fakultas = fakultas;
	}
	
	public Mahasiswa() {
		super();
	}

	public Long getIdMahasiswa() {
		return idMahasiswa;
	}

	public void setIdMahasiswa(Long idMahasiswa) {
		this.idMahasiswa = idMahasiswa;
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

	public Fakultas getFakultas() {
		return fakultas;
	}

	public void setFakultas(Fakultas fakultas) {
		this.fakultas = fakultas;
	}

	@Override
	public String toString() {
		return "Mahasiswa [idMahasiswa=" + idMahasiswa + ", namaMahasiswa=" + namaMahasiswa + ", alamatMahasiswa="
				+ alamatMahasiswa + ", fakultas=" + fakultas + "]";
	}
	
}
