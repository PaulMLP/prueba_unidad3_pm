package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IVentaRepository;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Venta;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaRepository ventaRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void crearVenta(List<DetalleVenta> detalles, String cedulaCliente, String numeroVenta, BigDecimal totalVenta) {
		Venta venta = new Venta();
		venta.setCedulaCliente(cedulaCliente);
		venta.setNumero(numeroVenta);
		venta.setFecha(LocalDateTime.now());
		venta.setDetalles(detalles);
		venta.setTotalVenta(totalVenta);
		this.ventaRepository.insertar(venta);
	}

}
