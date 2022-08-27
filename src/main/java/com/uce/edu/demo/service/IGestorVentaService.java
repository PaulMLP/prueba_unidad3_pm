package com.uce.edu.demo.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.ProductoVenta;

public interface IGestorVentaService {
	public void realizarVenta(List<ProductoVenta> listaProductos, String cedulaCliente, String numeroVenta)
			throws SQLException;

	public void reporteVenta(LocalDateTime fechaVenta, String categoria, Integer cantidad);

}
