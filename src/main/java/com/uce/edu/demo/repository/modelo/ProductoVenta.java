package com.uce.edu.demo.repository.modelo;

public class ProductoVenta {
	private String codigoBarras;
	private Integer cantidad;
	
	public ProductoVenta() {
		// TODO Auto-generated constructor stub
	}

	public ProductoVenta(String codigoBarras, Integer cantidad) {
		this.codigoBarras = codigoBarras;
		this.cantidad = cantidad;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
