package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.util.List;

import com.uce.edu.demo.repository.modelo.DetalleVenta;

public interface IVentaService {

	public void crearVenta(List<DetalleVenta> detalles, String cedulaCliente, String numeroVenta, BigDecimal totalVenta);
}
