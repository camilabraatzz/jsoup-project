package com.example.jsoupproject;

import java.util.Date;

public class Servico {
	private int id;
	private String estado;
	private String status;
	private Date data;
	
	public Servico(int id, String estado, String status, Date data) {
		super();
		this.id = id;
		this.estado = estado;
		this.status = status;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
